<?php

$page_title = 'Admin - Delete User';
include('lib/header.html');
echo '<h1>Delete a User</h1>';

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
	if($_POST['sure'] == 'Yes')
	{
		$q = "DELETE FROM users WHERE user_id=$id LIMIT 1";
		
		$r = @mysqli_query($dbc, $q);
		
		if(mysqli_affected_rows($dbc) == 1)
		{
			echo '<p>The user has been deleted.</p>';
		}
		else
		{
			echo '<p class="error">The user could not be deleted due to a system error.</p>';
			echo '<p>' . mysqli_error($dbc) . '<br />Query: ' . $q . '</p>';
		}
	}
	else
	{
		echo '<p>The user has NOT been deleted.</p>';
	}
}
else
{
	$q = "SELECT CONCAT(last_name, ', ', first_name) FROM users WHERE user_id=$id";
	
	$r = @mysqli_query($dbc, $q);
	
	if(mysqli_num_rows($r) == 1)
	{
		$row = mysqli_fetch_array($r, MYSQLI_NUM);
		
		echo '<form action="deluser.php" method="post">
				<h3>Name: ' . $row[0] . '</h3>
				<p>Are you sure you want to delete this user?<br />
				<input type="radio" name="sure" value="Yes" /> Yes 
				<input type="radio" name="sure" value="No" checked="checked" /> No</p>
				<p><input type="submit" name="submit" value="Submit" /></p>
				<input type="hidden" name="submitted" value="TRUE" />
				<input type="hidden" name="id" value="' . $id . '" />
			</form>';
	}
	else
	{
		echo '<p class="error">This page has been accessed in error.</p>';
	}
}

mysqli_close($dbc);		
include('lib/footer.html');

?>
