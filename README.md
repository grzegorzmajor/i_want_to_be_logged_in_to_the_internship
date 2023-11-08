# i_want_to_be_logged_in_to_the_internship
This is a recruitment task for an internship

If you want to run this project (in IntelliJ) after downloading from GitHub, set your data in the docker-compose.yml file and just click "package" in the maven tab, and run docker-compose.yml

If you just want to test, you simply need to set two environment variables at the end of the docker-compose.yml file: EMAIL_USER and EMAIL_PASS (for SMTP server from Google for your mailbox).
In the case of a Google email account, you must have two-step login enabled and create an application password in the Google account settings.
For another mailbox, you need to set two more environment variables: EMAIL_HOST, EMAIL_PORT.
