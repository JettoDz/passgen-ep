package passgen.controllers;

import static java.util.Objects.isNull;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PassgenController {
	
	private final static Logger LOGGER = Logger.getLogger(PassgenController.class.getName());
	
	private static final String DEFAULT_SALT = gensalt(12);
	
	@GetMapping("hash")
	public ResponseEntity<String> hash(@RequestParam(required = false) Integer salt, @RequestParam String src) {
		return ResponseEntity.ok().body(hashpw(src, isNull(salt) ? DEFAULT_SALT : gensalt(salt)));
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> missingStringParam(MissingServletRequestParameterException e) {
		LOGGER.severe(e.getMessage());
		return ResponseEntity.badRequest().body("Target string (src) must be specified.");
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> badLogRounds(IllegalArgumentException e) {
		LOGGER.severe(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The value for salt must be over 4 and up to 31.");
	}
	
}
