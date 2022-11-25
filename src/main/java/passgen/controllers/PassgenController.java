package passgen.controllers;

import static java.util.Objects.isNull;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import passgen.util.Logging;

@RestController
@RequestMapping("/")
public class PassgenController implements Logging {
	
	private static final String DEFAULT_SALT = gensalt(12);
	
	private static final String MISSING_SRC = "Target string (src) must be specified.";
	private static final String INVALID_SALT = "The value for salt must be over 4 and up to 31.";
	
	@GetMapping("hash")
	public ResponseEntity<String> hash(@RequestParam(required = false) Integer salt, @RequestParam String src) {
		return ResponseEntity.ok().body(hashpw(src, isNull(salt) ? DEFAULT_SALT : gensalt(salt)));
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> missingStringParam(MissingServletRequestParameterException e) {
		error(MISSING_SRC, e);
		return ResponseEntity.badRequest().body(MISSING_SRC);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> badLogRounds(IllegalArgumentException e) {
		error(INVALID_SALT, e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INVALID_SALT);
	}
	
}
