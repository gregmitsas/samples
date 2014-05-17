<?php 

$page_title = 'Admin - Change Password';
require_once('lib/config.inc.php'); 
include('lib/header.html');

if(!isset($_SESSION['first_name']) || !$_SESSION['user_level']==1)
{
	$url = BASE_URL . 'index.php';
	ob_end_clean();
	header("Location: $url");
	exit();
}

if((isset($_GET['id'])) && (is_numeric($_GET['id'])))
{
	$id = $_GET['id'];
}
elseif((isset($_POST['id'])) && (is_numeric($_POST['id'])))
{
	$id = $_POST['id'];
}
else
{
	echo '<p class="error">Access Denied.</p>';
	include('lib/footer.html'); 
	exit();
}

require_once (MYSQL);

if(isset($_POST['submitted']))
{
	$p = FALSE;
	
	if(preg_match ('/^(\w){4,20}$/', $_POST['password1']))
	{
		if($_POST['password1'] == $_POST['password2'])
		{
			$p = mysqli_real_escape_string($dbc, $_POST['password1']);
		}
		else
		{
			echo '<p class="error">New password did not match the confirmed password.</p>';
		}
	}
	else
	{
		echo '<p class="error">Please enter a valid password.</p>';
	}
	
	if($p)
	{
		$q = "UPDATE users SET pass=SHA1('$p') WHERE user_id=$id LIMIT 1";
		
		$r = mysqli_query($dbc, $q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
		
		if(mysqli_affected_rows($dbc) == 1)
		{
			$q = "SELECT email, first_name, last_name FROM users WHERE user_id=$id";
					
			$r = @mysqli_query($dbc, $q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));

			$q2 = "SELECT email FROM users WHERE user_id={$_SESSION['user_id']}";
			
			$r2 = @mysqli_query($dbc, $q2) or trigger_error("Query: $q2\n<br />MySQL Error: " . mysqli_error($dbc));

			if(mysqli_num_rows($r) == 1 && mysqli_num_rows($r2) == 1)
			{
				$row = mysqli_fetch_array($r, MYSQLI_NUM);

				$row2 = mysqli_fetch_array($r2, MYSQLI_NUM);

				$body = "Your password to log into this site has been changed to '$p'. Please log in using this password and this email address. You can change this password afterwards.";
				
				mail($row[0], 'Your new password.', $body, 'From: '. $row2[0]);

			}
			else
			{
				echo '<p class="error">Non valid SELECT-Query.</p>';
			}

			$apostrophe ="'";
		
			echo '<h3>' . $row[2] . ' ' . $row[1] . $apostrophe . 's password has been changed from ' . $row2[0]. '.</h3>';
			
			mysqli_close($dbc);
			include('lib/footer.html');
			exit();
		}
		else
		{
			echo '<p class="error">Password was not changed. Make sure the new password is different than the current one.</p>'; 
		}
	}
	else
	{
		echo '<p class="error">Please try again.</p>';		
	}
}

$q = "SELECT first_name, last_name FROM users WHERE user_id=$id";	
	
$r = @mysqli_query($dbc, $q);

if(mysqli_num_rows($r) == 1)
{
	$row = mysqli_fetch_array($r, MYSQLI_NUM);
	
	echo '<h1>Change the Password for user ' . $row[1] . ', ' . $row[0]. ' </h1>
			<form action="apassword.php" method="post">
				<fieldset>
				<p><b>New Password:</b> <input type="password" name="password1" size="20" maxlength="20" /> 
				<small>Use only letters, numbers, and the underscore. Must be between 4 and 20 characters long.</small></p>
				<p><b>Confirm New Password:</b> <input type="password" name="password2" size="20" maxlength="20" /></p>
				</fieldset>
				<div align="center"><input type="submit" name="submit" value="Change Password" />
				<input type="hidden" name="submitted" value="TRUE" />
				<input type="hidden" name="id" value="' . $id . '" /></div>
			</form>';
}
else
{
	echo '<p class="error">Erroneous ID.</p>';
}

mysqli_close($dbc);
include('lib/footer.html');

?>
