<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Query Results</title>
</head>

<body>

<h1>Query Results</h1>

<%@ page import="javax.naming.*,java.sql.*,javax.sql.*,java.util.*" %>
<%
String sql = request.getParameter("sql");
int update = 0;

//Check first word to see what kind of statement it is
StringTokenizer tok = new StringTokenizer(sql," ) (");
String sql_type = new String();

if(tok.hasMoreTokens())
	sql_type = tok.nextToken();

Context i = new InitialContext();
Context e = (Context) i.lookup("java:/comp/env");
DataSource d = (DataSource) e.lookup("jdbc/testdb");
Connection con = d.getConnection();

//used to get a list of all tables 
DatabaseMetaData meta = con.getMetaData();

Statement st = con.createStatement();

//try catch block
try{
if(sql_type.equalsIgnoreCase("select"))
{
	int found = 0;
	
	String name = new String();
	//get the name of the table
	if(tok.hasMoreTokens())
	{
		name = tok.nextToken();
		if(tok.hasMoreTokens())
		{
			name = tok.nextToken();
			if(tok.hasMoreTokens())
				name = tok.nextToken();
			
		
		}
	}
		
			
			
	//check to see if a table with that name already exists
	ResultSet res = meta.getTables(null,null,null,null);

      	while (res.next()) 
	{
        	
       		if(name.equalsIgnoreCase(res.getString("TABLE_NAME")))
		{
			
			found = 1;
			break;
		}
    	}
      	res.close();

	if(found==0)
		out.write("Table " + name + " does not exist.");
	else
	{
		ResultSet rs = st.executeQuery(sql);
	
		ResultSetMetaData md = rs.getMetaData();
%>


<table border="1" summary="query results">
<tr>
<% for (int c=1; c<=md.getColumnCount(); c++) { %>
  <th><%= md.getColumnName(c)%></th>
<% } %>
</tr>

<% while (rs.next()) { %>
  <tr>
  <% for (int c=1; c<=md.getColumnCount(); c++) { %>
    <td><%= rs.getString(c)%></td>
  <% } %>
  </tr>
<% } %>
</table>

<% 
	}
}

if(sql_type.equalsIgnoreCase("create") || sql_type.equalsIgnoreCase("drop"))
{
	update = 0;
	int found = 0;
	
	String name = new String();
	//get the name of the table
	if(tok.hasMoreTokens())
		if(tok.hasMoreTokens())
		{
			name = tok.nextToken();
			name = tok.nextToken();
		}
	//check to see if a table with that name already exists
	ResultSet res = meta.getTables(null,null,null,null);

      	while (res.next()) 
	{
        
       		if(name.equalsIgnoreCase(res.getString("TABLE_NAME")))
		{
			found = 1;
			break;
		}
    	}
      	res.close();
		
	if(found==1 && sql_type.equalsIgnoreCase("drop") )
	{
		update = st.executeUpdate(sql);
		out.write("Table " + name + " dropped.");
		
	}
	else
		if(found==0 && sql_type.equalsIgnoreCase("drop"))
			out.write("Table " + name + " does not exist.");
	
	if(found==0 && sql_type.equalsIgnoreCase("create") )
	{
		update = st.executeUpdate(sql);
		out.write("Table " + name + " created.");
	}
	else
		if(found==1 && sql_type.equalsIgnoreCase("create"))
			out.write("Table " + name + " already exists.");
}	

if(sql_type.equalsIgnoreCase("insert") || sql_type.equalsIgnoreCase("delete"))
{
	update=0;
	int found = 0;
	
	String name = new String();
	//get the name of the table
	if(tok.hasMoreTokens())
		if(tok.hasMoreTokens())
		{
			name = tok.nextToken();
			name = tok.nextToken();
		}
	//check to see if a table with that name already exists
	ResultSet res = meta.getTables(null,null,null,null);

      	while (res.next()) 
	{
        
       		if(name.equalsIgnoreCase(res.getString("TABLE_NAME")))
		{
			found = 1;
			break;
		}
    	}
      	res.close();
	
	if(found == 1)
	{
		update=st.executeUpdate(sql);
		if(sql_type.equalsIgnoreCase("insert"))
			out.write(update + " rows have been inserted");
		else
			out.write(update + " rows have been deleted");
	}
	else
		out.write("Table " + name + " does not exist.");	
	
}

}
catch (SQLException ex) 
	{ 
		out.write("Invalid SQL statement.");
		con.close();
	}


 con.close(); %>

</body>
</html>

