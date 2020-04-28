<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
        var index = <%=Integer.parseInt(request.getParameter("last"))%>;
        var user_id = <%=Integer.parseInt(request.getParameter("id"))%>;
        var answer_part = 0;
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <title>Test</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        #answer
        {
            white-space:pre-wrap;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function()
        {
            $("#show_answer").click(function()
            {
                $.ajax(
                    {
                        type: "GET",
                        data: {id: index, answer_part: answer_part + 1},
                        url : '/get_answer',
                        success: function(result)
                        {
                            answer_part++;
                            $("#answer").html(result);
                            $("#answer_number").html(answer_part);
                        }
                    });
            });

            $("#next_question").click(function()
            {
                $.ajax(
                    {
                        type: "GET",
                        data: {id: index + 1, user_id: user_id},
                        url : '/next_question',
                        success: function(result)
                        {
                            index++;
                            answer_part = 0;
                            $("#number").html(index);
                            $("#question").html(result);
                            $("#answer").html("");
                            $("#answer_number").html("");
                        }
                    });
            });

            $("#prev_question").click(function()
            {
                $.ajax(
                    {
                        type: "GET",
                        data: {id: index - 1, user_id: user_id},
                        url : '/prev_question',
                        success: function(result)
                        {
                            index--;
                            answer_part = 0;
                            $("#number").html(index);
                            $("#question").html(result);
                            $("#answer").html("");
                            $("#answer_number").html("");
                        }
                    });
            });
        })
    </script>
</head>
<body>
<table>
    <tr>
        <th>Question #<div id="number">${number}</div></th>
        <th>Answer #<div id="answer_number"></div></th>
    </tr>
    <tr>
        <td><div id="question">${question}</div></td>
        <td><div id="answer"></div></td>
    </tr>
</table>
<input type="button" value="Prev Question" id="prev_question" />
<input type="button" value="Next Question" id="next_question" />
<input type="button" value="Show Answer" id="show_answer" />
</body>
</html>
