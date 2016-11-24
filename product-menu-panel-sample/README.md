## Disclaimer

This repo can be used as a simple tutorial for creating field types in the Liferay Form app.

### What are field types?

The form field types are components used by a form to receive input from a user. They can have a variety of forms and shapes and specially different UX to get data from the user who is filling up the form. Developers may create new custom field types to provide users with a better form filling experience.

### Default Form field types

As the initial version of the Forms, six different field types are bundled by default:


1. Form Text - Used to display any text to the user, it can be an information to help users to fill the form.
2. Text field - Used to get text data from the user, it can assume a single or multi line view. 
3. Select from list - Used to to make the user choose one alternative from a list of options for selection. A data provider can also be attached to this field type to populate the option values and the options are displayed in a combo box.
4. Single selection - Similar to item 3, but currently the only way to add options is manually and the options are displayed as "radio" buttons.
Date - Used to get a specific date, it can be used to ask the user his birthday for example.
5. Multiple Selection - Similarly to item 3 and 4 it displays a list of options for user selection. However the user can choose more the one option. The option values are displayed as "checkboxes".

![Default field types](/static/default_field_types.png)

