<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>Your To-Dos:</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Description</th>
            <th>Target Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${toDos}" var="todos">
            <tr>
                <td>${todos.description}</td>
                <td>${todos.targetDate}</td>
                <td>${todos.done}</td>
                <td><a href="update-todo?id=${todos.id}" class="btn btn-success">Update</a></td>
                <td><a href="delete-todo?id=${todos.id}" class="btn btn-danger">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="add-todo" class="btn btn-success">Add To-Do</a>
</div>
<%@ include file="common/footer.jspf" %>