<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">



<head>	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>Ball 4 Ever</title>
	
	<link rel="stylesheet" type="text/css" href="style.css" />
	<!--[if lt IE 8]>
		<link rel="stylesheet" type="text/css" href="style-ie.css" />
	<![endif]-->
</head>

<body>
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*,java.util.*" %>
<%

//HASHMAP TO STORE months
HashMap map = new HashMap();
map.put("January","01");
map.put("February","02");
map.put("March","03");
map.put("April","04");
map.put("May","05");
map.put("June","06");
map.put("July","07");
map.put("August","08");
map.put("September","09");
map.put("October","10");
map.put("November","11");
map.put("December","12");

Context i = new InitialContext();
Context e = (Context) i.lookup("java:/comp/env");
DataSource d = (DataSource) e.lookup("jdbc/testdb");
Connection con = d.getConnection();
Statement st = con.createStatement();
int update = 0;

//create the date into a sql compatible format DATE('yyyy-mm-dd')
String date = new String("DATE('"+request.getParameter("year")+"-"+map.get(request.getParameter("month"))+"-"+request.getParameter("day")+"')");

//get parameters and insert into database
String adduser = new String("insert into users values ('" 
		+ request.getParameter("f_name") + "','"
		+ request.getParameter("l_name") + "','"
		+ request.getParameter("country") + "','" 
		+ request.getParameter("city") + "'," + date +",'"
		+ request.getParameter("email") + "','" 		
		+ request.getParameter("user") + "','" 
		+ request.getParameter("pass") + "')");

//we have the query, time to add. 
update = st.executeUpdate(adduser);


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
						
				<p>Thank you for registering!</p>
				
				
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
