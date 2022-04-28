# passgen-ep
Small REST app for encrypting Strings with BCrypt.

If you find it useful but incomplete, feel free to fork from it to properly fit your needs/security standards. 🐐

Do keep in mind, however, that this is inteded to be used only in local machines or in secured, private networks only accesible to machines within said network.

### Usage:

After mounting/booting/running, simply call

    http://<<your_host>>/hash?src=<<password>>

This will automatically generate a hashed password (salt 12) of the given string. If you wish to use a particular salt, use

    http://<<your_host>>/hash?src=<<password>>&salt=<<salt>>

... instead. It uses SpringSecurity's implementation of BCrypt so it only supports salts from 4 up to 31.
