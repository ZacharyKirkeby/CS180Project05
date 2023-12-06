**USER TEST CASES**

Test 1: User registration

Steps:

1. User launches application.
2. User selects the "Register" button.
3. User selects the "Email" textbox.
4. User enters email via the keyboard. 
5. User selects the "Username" textbox. 
6. User enters username via the keyboard.
7. User selects the "Password" textbox.
8. User selects the role.
9. User selects the "Register" button.

Expected result: Application creates the user's account and loads their homepage automatically.

Test Status: Passed.

Test 2: User log in

Steps:

1. User launches application.
2. User selects the "Log in" button.
2. User selects the username textbox.
3. User enters username via the keyboard.
4. User selects the password textbox.
5. User enters the password via the keyboard.

Expected result: Application verifies the user's email/username and password and loads their homepage automatically.

Test Status: Passed.

Test 3: User logout

1. After logging in (User Test Cases - Test 2)
2. User selects the "Logout" button.

Expected result: Application logs out the user and loads the login/register page automatically.

Test Status: Passed.

Test 4: User Change Username

1. After logging in (User Test Cases - Test 2)
2. User selects the "Manage Account" button.
3. User selects the "Change Username" button. 
4. User selects the "New Username" textbox. 
5. User enters the new username via the keyboard. 
6. User selects the "Change" button. 
7. User sees the "Username Changed" page. 
8. User selects the "OK" button. 
9. User selects the red circle to exit the "Change Username" page. 
10. User selects the red circle to exit the "Manage Account" page.
11. User selects the "Logout" button.
12. User logs in with the new username. (User Test Cases - Test 2)

Expected result: Application logs in the user and loads their homepage automatically.

Test Status: Passed.

Test 5: User Change Password

1. After logging in (User Test Cases - Test 2)
2. User selects the "Manage Account" button.
3. User selects the "Change Password" button.
4. User selects the "Old Password" textbox.
5. User enters the old password via the keyboard.
6. User selects the "New Password" textbox.
6. User enters the new password via the keyboard.
7. User selects the "Change" button. 
8. User sees the "Password Changed" page. 
9. User selects the "OK" button.
10. User selects the red circle to exit the "Change Password" page.
11. User selects the red circle to exit the "Manage Account" page.
12. User selects the "Logout" button.
13. User logs in with the new password. (User Test Cases - Test 2)

Expected result: Application logs in the user and loads their homepage automatically.

Test Status: Passed.

Test 6: User Change Role

1. After logging in (User Test Cases - Test 2)
2. User selects the "Manage Account" button.
3. User selects the "Change Role" button.
4. User selects the "Password" textbox.
5. User enters the password via the keyboard.
6. User selects the "Change To Buyer"/"Change To Seller" button depending on which role they are.
7. (fixing error) User sees the "Role Changed" page.
8. User selects the "OK" button.
9. User selects the red circle to exit the "Change Role" page.
10. User selects the red circle to exit the "Manage Account" page.
11. User selects the "Logout" button.
12. User logs in with the new role. (User Test Cases - Test 2)

Expected result: Application logs in the user and loads their homepage automatically.

Test Status: Failed. 

Test 7: User Delete Account

1. After logging in (User Test Cases - Test 2)
2. User selects the "Manage Account" button.
3. User selects the "Delete Account" button.
4. User selects the "Password" textbox.
5. User enters the password via the keyboard.
6. User sees the "Account Deleted" page
7. User selects the "OK" button.
8. User sees the login/register page automatically.
9. User tries to login with the deleted account username and password. (User Test Cases - Test 2)

Expected result: Application loads the "Login Failed" page because the account has been deleted.

Test Status: Passed.



**SELLER MARKETPLACE TEST CASES**


**BUYER MARKETPLACE TEST CASES**
