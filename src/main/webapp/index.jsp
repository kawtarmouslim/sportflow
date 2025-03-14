<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil | SportFlow</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
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
            background: linear-gradient(135deg, #6e7078, #004c99);
            color: #fff;
            padding: 15px 0;
            position: sticky;
            top: 0;
            z-index: 1000;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
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

        /* Hero Section */
        .hero {

            height: 60vh;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: white;
            padding: 20px;
            position: relative;
        }

        .hero::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
        }

        .hero-content {
            position: relative;
            max-width: 700px;
        }

        .hero h2 {
            font-size: 2.5rem;
            margin-bottom: 15px;
        }

        .hero p {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }

        .btn {
            background: #ff9800;
            color: white;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s;
        }

        .btn:hover {
            background: #e68900;
        }

        /* Features Section */
        .features {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin: 40px auto;
            width: 90%;
            max-width: 1200px;
        }

        .feature-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: 0.3s;
        }

        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.15);
        }

        .feature-card h3 {
            color: #007bff;
            margin-bottom: 10px;
        }

        /* Footer */
        footer {
            background: #343a40;
            color: white;
            padding: 15px 0;
            text-align: center;
            margin-top: 50px;
            font-size: 0.9rem;
        }
    </style>
</head>

<body>
<header>
    <div class="container">
        <h1>SportFlow</h1>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/">Accueil</a></li>
                <li><a href= onclick="window.location.href='<%= request.getContextPath() %>/seance?action=listseance'">Séances</a></li>
                <li><a href= onclick="window.location.href='<%= request.getContextPath() %>/user?action=listuser'">Utilisateurs</a></li>
                <li><a href="#">Déconnexion</a></li>
            </ul>
        </nav>
    </div>
</header>

<section class="hero">
    <div class="hero-content">
        <h2>Gérez votre club sportif en toute simplicité</h2>
        <p>Une plateforme intuitive pour les clubs, entraîneurs et membres.</p>
        <a href="#" class="btn">Commencer</a>
    </div>
</section>

<section class="features">
    <div class="feature-card" onclick="window.location.href='<%= request.getContextPath() %>/seance?action=listseance'">
        <h3>Gestion des Séances</h3>
        <p>Créez, planifiez et suivez vos séances d'entraînement facilement.</p>
    </div>
    <div class="feature-card" onclick="window.location.href='<%= request.getContextPath() %>/user?action=listuser'">
        <h3>Gestion des Utilisateurs</h3>
        <p>Ajoutez et gérez les membres et entraîneurs avec leurs spécialités.</p>
    </div>
    <div class="feature-card">
        <h3>Statistiques et Suivi</h3>
        <p>Visualisez l’évolution des membres et des performances.</p>
    </div>
</section>

<footer>
    <p>&copy; 2025 SportFlow. Tous droits réservés.</p>
</footer>
</body>

</html>