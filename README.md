# NoviBootCamp

This spring boot application allows users to be signed up as different roles. Each with their own rights.

## Description
The application has multiple roles:
* Students
* Teachers
* Parents
* Admins

The Admins can create classrooms and appoint them to a location. Then the admin can add students to the classrooms.
Teachers can cancel and view their classes.
Students can view their classes.
Parents can see their children's classes.

## Endpoints
```
[GET] / (this one is empty) 
```
```
[GET] /Redirect (this one sends the user to their corresponding page after login)
```
```
[GET] /login (this page handles the user login)
```
```
[POST] /logout (Allowed you to log out)
```
```
[GET] /testError (Throws an error to test the error page)
```
###Admin pages, These need admin rights
```
[GET] /Admin/RegisterUser (Shows form to create a new user)
```
```
[POST] /Admin/RegisterUser (Creates a new user)
```
```
[GET] /Admin/RemoveUser (Shows form to remove a user)
```
```
[POST] /Admin/RemoveUser (Removes the user)
```
```
[GET] /Admin/UpdateUser (Shows page to change user)
```
```
[POST] /Admin/UpdateUser (Changes existing user, if no existing found create a new one)
```
```
[GET] /Admin/ShowUsers (Shows all users)
```
