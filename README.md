## AnhyExtension

The **AnhyExtension** for PlaceholderAPI provides a series of placeholders that integrate with AnhyLibAPI and other plugins developed by AnhyDev. This extension requires the AnhyLibAPI library to be installed for all functionality.

### Placeholders List (Requires AnhyLibAPI)

#### Gender-related Placeholders (Requires AnhyFamily Plugin)

- `%anhy_gender%`
  - Returns the player's gender.
- `%anhy_gender_key%`
  - Returns the language file key for the player's gender.
- `%anhy_gender_lang%`
  - Returns the name of the gender in the player's language.
- `%anhy_gender_hexcolor%`
  - Returns the HEX color code associated with the player's gender.

#### Family-related Placeholders (Requires AnhyFamily Plugin)

- `%anhy_family_firstname%`
  - Returns the player's first name.
- `%anhy_family_lastname%`
  - Returns the player's last name based on gender.
- `%anhy_family%`
  - Returns a serialized JSON string of the player's Family object, which can be processed in any way convenient to the user.
- `%anhy_family_mother%`
  - Returns the UUID string of the player's mother.
- `%anhy_family_father%`
  - Returns the UUID string of the player's father.
- `%anhy_family_spouse%`
  - Returns the UUID string of the player's spouse.
- `%anhy_family_children%`
  - Returns a comma-separated list of UUID strings of the player's children.
- `%anhy_family_info%`
  - Returns detailed information about the player's family in language file keys.
- `%anhy_family_info_translated%`
  - Returns detailed information about the player's family translated into the player's language.
- `%anhy_family_tree%`
  - Returns a textual representation of the player's family tree in language file keys.
- `%anhy_family_tree_translated%`
  - Returns a textual representation of the player's family tree translated into the player's language.

#### Language-related Placeholders (Requires AnhyLibAPI)

- `%anhy_lang%`
  - Returns the player's primary language.
- `%anhy_langs%`
  - Returns all languages set for the player, formatted as a comma-separated string.

#### Dynamic Language Placeholders (Requires AnhyLingo Plugin)

- `%anhy_@language_key%`
  - The prefix `%anhy_@` is used for dynamic language placeholders. Following the prefix is the language key, whose translation is stored in the AnhyLingo plugin folder located at `plugins/AnhyLingo/system/language_files...` on the server. This placeholder dynamically determines the player's chosen language and returns the translation for the specified key in the player's language.

- `%anhy_@some_placeholder@arg0,,arg1,,arg2,,arg3%`
  - This placeholder extends the dynamic language placeholders with additional parameters. The prefix `%anhy_@` is followed by `some_placeholder` and optional parameters (`arg0,,arg1,,arg2,,arg3`). Each parameter is separated by double commas `,,`. The `some_placeholder` part is processed as a regular dynamic placeholder, while the additional parameters are formatted and translated into the player's language before being used in the final output. For example, `%anhy_@welcome_message@name,,world,,example%` will return the translated welcome message with `name`, `world`, and `example` appropriately formatted and translated. If the translated text for the key `welcome_message` contains placeholders (e.g., "Welcome %s to our server %s, don't forget to follow us on Twitter %s, and join our Discord %s"), each `%s` will be replaced by the corresponding value from the array `arg0,,arg1,,arg2,,arg3` in the order they are provided. Note that these additional parameters can also be language keys, which will be translated into the player's language.
---