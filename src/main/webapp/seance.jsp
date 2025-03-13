<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Séance</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card p-4">
        <h3 class="text-center mb-3">Ajouter une Séance</h3>
        <% String message = request.getParameter("message");
            if (message != null) { %>
        <div class="alert alert-success"><%= message %></div>
        <% } %>

        <form  action="seance?action=create" method="post">
            <input type="hidden" name="action" value="create">

            <div class="mb-3">
                <label for="date" class="form-label">Date et Heure :</label>
                <input type="datetime-local" id="date" name="date" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="member_id" class="form-label">Membre :</label>
                <input type="number" id="member_id" name="member_id" class="form-control" placeholder="ID du membre" required>
            </div>

            <div class="mb-3">
                <label for="entraineur_id" class="form-label">Entraîneur :</label>
                <input type="number" id="entraineur_id" name="entraineur_id" class="form-control" placeholder="ID de l'entraîneur" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Ajouter</button>
        </form>
    </div>
</div>
</body>
</html>
