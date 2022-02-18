<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>login</title>
</head>
<body>
	<h1>Spring MVC Security - Login with Google</h1>
	<h2>${message}</h2>
	
	<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/SpringMvcGoogle/login-google&response_type=code
		&client_id=127492257645-9j4f1o189sq15fmg41dr4bmc8u3lv53s.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>	

	<form name='loginForm' action="<c:url value='j_spring_security_login' />" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="login" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	</form>
</body>
</html>