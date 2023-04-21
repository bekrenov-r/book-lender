# Book lending manager
This manager will help you keep track on books you lend to your friends.
You can add new lend with data about the book itself, the person who borrows it and date until which book should be returned.
Also, you can edit and delete lends, and search for exact record by keyword.

### How to run this application
1. You will need MySQL8 environment installed.
2. Open <b>hibernate.cfg.xml</b> file. Replace <b>your_username</b>
   and <b>your_password</b> placeholders with your MySQL credentials.
3. Open MySQL Workbench. Run <b>create_database.sql</b> script from sql_scripts folder.
4. Run <b>App</b> class.

Technologies used: Java Swing, Hibernate, Maven