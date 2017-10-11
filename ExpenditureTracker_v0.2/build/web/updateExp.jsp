<%-- 
    Document   : updateExp
    Created on : 6-Sep-2017, 7:56:55 PM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Expenditure Updater</title>
    </head>
    <body>
        <h1>Update Your Expenditure</h1>



        <form action="/ExpenditureTracker_v0.2/update" method="post">
            <fieldset>
                <legend>Enter your expenditure</legend>
                Amount:<br /> <input name="amount" type="number" step="0.01" min="0.01" max="300000000000"/><br /><br />
                
                <fieldset>
                    <legend>What did you spend it on?</legend>
                    <input type="radio" name="ExpType" value="residence" checked> Residence 
                    <input type="radio" name="ExpType" value="textbooks"> Textbooks 
                    <input type="radio" name="ExpType" value="food"> Food <br>
                    <input type="radio" name="ExpType" value="tuition" checked> Tuition
                    <input type="radio" name="ExpType" value="electronics"> Electronics 
                    <input type="radio" name="ExpType" value="transportation"> Transportation <br>
                    <input type="radio" name="ExpType" value="other"> Other <br>
                    <input type="submit" value="Submit" />
                </fieldset>
            </fieldset>
        </form>
        <p>&nbsp;</p>

        <form action="/ExpenditureTracker_v0.2/getSpending" method="post">
            
                
            <input type="submit" value="Cancel" />

        </form>
        
        



    </body>
</html>


