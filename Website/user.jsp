<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>Ball 4 Ever</title>
	
	<link rel="stylesheet" type="text/css" href="style.css" />
	<!--[if lt IE 8]>
		<link rel="stylesheet" type="text/css" href="style-ie.css" />
	<![endif]-->
</head>

<body>
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*,java.util.*" %>
<%

Context i = new InitialContext();
Context e = (Context) i.lookup("java:/comp/env");
DataSource d = (DataSource) e.lookup("jdbc/testdb");
Connection con = d.getConnection();
Statement st = con.createStatement();

int found = 0;
String name = new String();
String location = new String();
String birth = new String();
String email = new String();
String user = new String();
String message = new String();

//search for the user in the database
String sql = "select username,password from users";
ResultSet rs = st.executeQuery(sql);
ResultSetMetaData md = rs.getMetaData();

//loo through the results and check to username and password
while(rs.next())
{
	if(rs.getString(1).equals(request.getParameter("user")) && rs.getString(2).equals(request.getParameter("pass")))
	{
		user = request.getParameter("user");
		found = 1;
		
		break;
	}
}

if(found == 1)
{
	message = "User Profile";
	//extract the data from the table
	ResultSet rs2 = st.executeQuery("select * from users where username='"+user+"'");
	while (rs2.next())
	{
		name = rs2.getString(1)+" "+rs2.getString(2);
		location = rs2.getString(4)+","+rs2.getString(3);
		birth = rs2.getString(5);
		email = rs2.getString(6);
	}
}
else
	message = "Username or password incorrect";


%>

	<div id="page-wrap">
		<div id="inside">
			
			<div id="top-menu">
				<table class="top-table">
					<tr><td><p class="menu"><a href="register.html" class="button1"><strong>Create Account</strong></a></p></td>
					<td><p class="menu"><a href="login.html" class="button1"><strong>Sign In</strong></a></p></td></tr>				
					
				</table>

			</div>
			<div id="header">
				<img src="banner.png" />
			</div>
			<div id="main-menu">
				<table class="main-table">
					<tr>
						<td><p class="cell"><a href="news.html">News</a></p></td>
						<td><p class="cell"><a href="pics.html">Photos</a></p></td>
						<td><p class="cell"><a href="vids.html">Videos</a></p></td>
						<td><p class="cell"><a href="courts.html">Courts</a></p></td>	
						<td><p class="cell"><a href="players.html">Ballers</a></p></td>	
					
					</tr>
				</table>
			</div>
			<div id="left-sidebar">
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div>   
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div>
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div> 
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div>  
				
			
			</div>
			<div id="right-sidebar">
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div>   
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div>
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div> 
				<div class="article">
				<p class="headline"><a href="article.html"><strong>Pistons lose in Chicago blowout.</strong></a></p>
				<p class="abstract">Pistons fail to match up to Derrick Rose's triple double...</p>
				</div> 
				
			</div>
			
			
			<div id="main-content">
				
				<div id="news">
					<div class="news-title">


	


						<p><strong><%= message %> </strong></p>
					</div>
					
					<div id="news-pic">
						<div >
							<img src="profile.png" alt="My Profile Pic" width="200" height="250" />
							
					        </div>  
					</div>
					<div class="user-info">

						<table>
							<tr><td class="user-info-cell"><p><strong>Name:</strong></p></td><td><p><%= name%></p></td></tr>
							<tr><td class="user-info-cell"><p><strong>Location:</strong></p></td><td><p><%= location%></p></td></tr>
							<tr><td class="user-info-cell"><p><strong>Date of birth:</strong></p></td><td><p><%= birth%></p></td></tr>
							<tr><td class="user-info-cell"><p><strong>E-mail address:</strong></p></td><td><p><%= email%></p></td></tr>
							
							
						</table>
					</div>
				</div>
			
			</div>
			
			
			
			<div id="footer">
			<table class="footer-links">
				<tr>
				<td><p><a class="footer-text" href="about.html">About us</a></p></td>
				<td><p class="footer-text">|</p></td>
				<td><p><a class="footer-text" href="contact.html">Contact</a></p></td>
				<td><p class="footer-text">|</p></td>
				<td><p><a class="footer-text" href="index.html">Home</a></p></td>
				</tr>
			</table>
				
				
			</div>
		
		</div>
		
		
	
	</div>

</body>

</html>
