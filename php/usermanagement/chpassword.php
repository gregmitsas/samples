<?php

$page_title = 'Change Password';
require_once('lib/config.inc.php'); 
include('lib/header.html');

if(!isset($_SESSION['first_name']))
{	
	$url = BASE_URL . 'index.php';
	ob_end_clean();
	header("Location: $url");
	exit();
}

if(isset($_POST['submitted']))
{
	require_once(MYSQL);
			
	$p = FALSE;
	
	if(preg_match('/^(\w){4,20}$/', $_POST['password1']))
	{
		if($_POST['password1'] == $_POST['password2'])
		{
			$p = mysqli_real_escape_string($dbc, $_POST['password1']);
		}
		else
		{
			echo '<p class="error">Your password did not match the confirmed password!</p>';
		}
	}
	else
	{
		echo '<p class="error">Please enter a valid password!</p>';
	}
	
	if($p)
	{
		$q = "UPDATE users SET pass=SHA1('$p') WHERE user_id={$_SESSION['user_id']} LIMIT 1";
		
		$r = mysqli_query($dbc, $q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
		
		if(mysqli_affected_rows($dbc) == 1)
		{
			echo '<h3>Your password has been changed.</h3>';
			
			mysqli_close($dbc);
			include('lib/footer.html');
			exit();
		}
		else
		{
			echo '<p class="error">Your password was not changed. Make sure your new password is different than the current password.';
		}

	}
	else
	{
		echo '<p class="error">Please try again.</p>';		
	}
	
	mysqli_close($dbc);
}

?>

<h1>Change Your Password</h1>
<form action="chpassword.php" method="post">
	<fieldset>
	<p><b>New Password:</b> <input type="password" name="password1" size="20" maxlength="20" /> <small>Use only letters, numbers, and the underscore. Must be between 4 and 20 characters long.</small></p>
	<p><b>Confirm New Password:</b> <input type="password" name="password2" size="20" maxlength="20" /></p>
	</fieldset>
	<div align="center"><input type="submit" name="submit" value="Change My Password" /></div>
	<input type="hidden" name="submitted" value="TRUE" />
</form>

<?php
include('lib/footer.html');
?>
