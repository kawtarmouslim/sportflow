<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Seance" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestion des Cours | SportFlow</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: #004c99;
            color: #333;
        }

        /* Header */
        header {
            background: linear-gradient(135deg, #000000, #1c1c1c);
            color: #fff;
            padding: 15px 0;
            position: sticky;
            top: 0;
            z-index: 1000;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        h1 {
            font-size: 1.8rem;
            font-weight: 600;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 20px;
        }

        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: 600;
            padding: 10px 15px;
            border-radius: 8px;
            transition: 0.3s;
            background: rgba(255, 255, 255, 0.2);
        }

        nav ul li a:hover {
            background: rgba(255, 255, 255, 0.4);
        }

        /* Section principale */
        .content {
            width: 90%;
            max-width: 1200px;
            margin: 40px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
        }

        .content h3 {
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
            color: #004c99;
        }

        /* Boutons */
        .btn-primary {
            background: #ff9800;
            border: none;
            padding: 10px 15px;
            font-weight: bold;
            border-radius: 8px;
            transition: 0.3s;
        }

        .btn-primary:hover {
            background: #e68900;
        }

        .btn-warning {
            background: #007bff;
            border: none;
            color: white;
            transition: 0.3s;
        }

        .btn-warning:hover {
            background: #0056b3;
        }

        .btn-danger {
            background: #e60000;
            border: none;
        }

        .btn-danger:hover {
            background: #b30000;
        }

        /* Tableau */
        .table {
            margin-top: 20px;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        .table th {
            background-color: #004c99;
            color: white;
            text-align: center;
        }

        .table tbody tr:hover {
            background: #f8f9fa;
        }

        .table td {
            text-align: center;
            vertical-align: middle;
        }

        /* Footer */
        footer {
            background: #1c1c1c;
            color: white;
            padding: 15px 0;
            text-align: center;
            margin-top: 50px;
            font-size: 0.9rem;
        }
    </style>
</head>

<body>

<!-- Navigation -->
<header>
    <div class="container">
        <h1>SportFlow</h1>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/">Accueil</a></li>
                <li><a href="<%=request.getContextPath()%>/list">Séances</a></li>
                <li><a href="<%=request.getContextPath()%>/users">Utilisateurs</a></li>
                <li><a href="<%=request.getContextPath()%>/contact">Contact</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- Main Content -->
<div class="content">
    <h3>Liste des utilisateur</h3>
    <hr>
    <div class="d-flex justify-content-start">
        <a href="<%= request.getContextPath() %>/user?action=newseance" class="btn btn-primary">Ajouter un utilisateur</a>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>DateTime Seance </th>
            <th>Nom Membre</th>
            <th>Nom Entraineur</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Seance> seances = (List<Seance>) request.getAttribute("seances");
            if (seances != null) {
                for (Seance seance : seances) {
        %>
        <tr>
            <td><%= seance.getIdSeance() %></td>
            <td><%= seance.getDateTime() %></td>
            <td><%= seance.getIdEntraineur() %></td>
            <td><%= seance.getIdMembre() %></td>


            <td>
                <a href="<%= request.getContextPath() %>/etudiant/edit?idEtudiant=<%= seance.getIdSeance() %>" class="btn btn-warning btn-sm">Modifier</a>
                <a href="<%= request.getContextPath() %>/etudiant/delete?idEtudiant=<%= seance.getIdSeance() %>" class="btn btn-danger btn-sm">Supprimer</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<footer>
    <p>&copy; 2025 SportFlow. Tous droits réservés.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
