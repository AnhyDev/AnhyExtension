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
- `%anhy_gender_symbol%`
  - Returns a symbol representing the player's gender.
- `%anhy_gender_mccolor%`
  - Returns the Minecraft color associated with the player's gender.
- `%anhy_gender_hexcolor%`
  - Returns the HEX color code associated with the player's gender.

#### Family-related Placeholders (Requires AnhyFamily Plugin)

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
  - Returns detailed information about the player's family.
- `%anhy_family_tree%`
  - Returns a textual representation of the player's family tree.

#### Language-related Placeholders (Requires AnhyLibAPI)

- `%anhy_lang%`
  - Returns the player's primary language.
- `%anhy_langs%`
  - Returns all languages set for the player, formatted as a comma-separated string.

These placeholders can be used in any text that supports PlaceholderAPI, enabling dynamic display of player information on your server. Utilizing these placeholders facilitates easy integration of detailed player information into Minecraft through other plugins or chat systems.
