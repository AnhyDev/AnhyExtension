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

---