<?php

$page_title = 'Admin - Change User Rights';
include('lib/header.html');
echo '<h1>Change User Rights</h1>';

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
	echo '<p class="error">This page has been accessed in error.</p>';
	include('lib/footer.html'); 
	exit();
}

require_once('mysqli_connect.php');

if(isset($_POST['submitted']))
{
	if($_POST['change'] == 'admin')
	{
		$q = "UPDATE users SET user_level=1 WHERE user_id=$id LIMIT 1";
		
		$r = @mysqli_query($dbc, $q);
		
		if(mysqli_affected_rows($dbc) == 1)
		{
			echo '<p>Admin rights assigned to this user.</p>';
		}
		else
		{
			echo '<p class="error">The user could not be updated due to a system error or the user is already an ' . $_POST["change"]. '.</p>';
			echo '<p>' . mysqli_error($dbc) . '<br />Query: ' . $q . '</p>';
		}
	} 
	else if($_POST['change'] == 'normal')
	{
		$q = "UPDATE users SET user_level=0 WHERE user_id=$id LIMIT 1";
		
		$r = @mysqli_query($dbc, $q);
		
		if(mysqli_affected_rows($dbc) == 1)
		{
			echo '<p>Regular rights assigned to this user.</p>';	
		}
		else
		{
			echo '<p class="error">The user could not be updated due to a system error or the user is already a ' . $_POST["change"]. ' user.</p>';
			echo '<p>' . mysqli_error($dbc) . '<br />Query: ' . $q . '</p>';
		}
	}
	else
	{
		echo '<p>The user has NOT been updated.</p>';	
	}
}
else
{
	$q = "SELECT CONCAT(last_name, ', ', first_name) FROM users WHERE user_id=$id";
	
	$r = @mysqli_query($dbc, $q);
	
	if(mysqli_num_rows($r) == 1)
	{
		$row = mysqli_fetch_array($r, MYSQLI_NUM);
		
		echo '<form action="urights.php" method="post">
				<h3>Name: ' . $row[0] . '</h3>
				<p>User Rights<br />
				<input type="radio" name="change" value="admin" /> Admin Rights 
				<input type="radio" name="change" value="normal" checked="checked" /> Regular Rights </p>
				<p><input type="submit" name="submit" value="Submit" /></p>
				<input type="hidden" name="submitted" value="TRUE" />
				<input type="hidden" name="id" value="' . $id . '" />
			</form>';
	}
	else
	{
		echo '<p class="error">Access Denied.</p>';
	}
}

mysqli_close($dbc);
include('lib/footer.html');

?>
