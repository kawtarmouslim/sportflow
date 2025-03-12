<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Add New User</h1>
    <form action="create" method="post">
        <div class="form-group">
            <label for="nom">Nom:</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
        </div>
        <div class="form-group">
            <label for="prenom">Prénom:</label>
            <input type="text" class="form-control" id="prenom" name="prenom" required>
        </div>
        <div class="form-group">
            <label for="date">Date Naissance:</label>
            <input type="date" class="form-control" id="date" name="date" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="tel">Télephone:</label>
            <input type="tel" class="form-control" id="tel" name="tel" pattern="[0-9]{10}" placeholder="10 chiffres" required>
        </div>

        <div class="form-group">
            <label for="role">Role:</label>
            <select class="form-control" id="role" name="role" required>
                <option value="member">Member</option>
                <option value="entraineur">Entraineur</option>
            </select>
        </div>

        <div class="form-group" id="specialityField" style="display:none;">
            <label for="specialite">Speciality (for entraineur):</label>
            <input type="text" class="form-control" id="specialite" name="specialite">
        </div>

        <div class="form-group" id="sportField" style="display:none;">
            <label for="sportPratique">Sport Practiced (for Member):</label>
            <input type="text" class="form-control" id="sportPratique" name="sportPratique">
        </div>

        <button type="submit" class="btn btn-primary">Add User</button>
    </form>
    <a href="admin-dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>

<script>
    document.getElementById("role").addEventListener("change", function () {
        var role = this.value;
        if (role === "trainer") {
            document.getElementById("specialityField").style.display = "block";
            document.getElementById("sportField").style.display = "none";
        } else if (role === "member") {
            document.getElementById("sportField").style.display = "block";
            document.getElementById("specialityField").style.display = "none";
        }
    });
</script>
</body>
</html>
