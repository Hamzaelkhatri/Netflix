-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 04 juil. 2021 à 00:58
-- Version du serveur :  10.1.36-MariaDB
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `android`
--

-- --------------------------------------------------------

--
-- Structure de la table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `img_thun` varchar(200) NOT NULL,
  `img_cover` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `rating` int(11) NOT NULL,
  `streamibgLink` varchar(200) NOT NULL,
  `original` int(11) NOT NULL,
  `season` int(11) NOT NULL,
  `Episode` int(11) NOT NULL,
  `title_ep` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `movie`
--

INSERT INTO `movie` (`id`, `title`, `img_thun`, `img_cover`, `description`, `rating`, `streamibgLink`, `original`, `season`, `Episode`, `title_ep`) VALUES
(1, 'House of card', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/house.jpg?alt=media&token=c77aa3be-dbb2-4092-b3f8-f5eac63a0777', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/house_cover.jpg?alt=media&token=f34e7b9e-94f8-48de-b45e-37e83c73501f', 'House of Cards is set in Washington, D.C. and is the story of Congressman Frank Underwood (Kevin Spacey), a Democrat from South Carolina\'s 5th congressional district and House Majority Whip, and his equally ambitious wife Claire Underwood (Robin Wright). Frank is passed over for appointment as Secretary of State, so he initiates an elaborate plan to attain power, aided by Claire. The series deals with themes of ruthless pragmatism, manipulation, betrayal, and power.', 8, 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/houseOfCard.mp4?alt=media&token=5efc97cc-1fcc-4169-9177-5dcf232188e4', 1, 0, 0, NULL),
(3, 'flash', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/flash.jpg?alt=media&token=3b42932e-d2ba-4d3e-a56b-3c2ec91042f7', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/flash_cover.jpg?alt=media&token=8ac45f09-a4c3-42ee-aec9-7099beee89f2', 'The Flash is an American superhero television series developed by Greg Berlanti, Andrew Kreisberg, and Geoff Johns, airing on The CW. It is based on the DC Comics character Barry Allen / Flash, a costumed superhero crime-fighter with the power to move at superhuman speeds.', 8, 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/Flash.mp4?alt=media&token=ac805b10-1d69-4983-a211-6b8bd1844e35', 0, 1, 1, '1.FLASH PLAYER haah'),
(4, 'B,nine-nine', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/broklyn.jpg?alt=media&token=6d797a25-30fc-498d-9744-84e63f6b889d', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/brooklyn_cover.jpg?alt=media&token=76b8ca3e-a7c3-4efd-aad1-8232966f69ef', 'Brooklyn Nine-Nine is an American police procedural comedy television series created by Dan Goor and Michael Schur. The series revolves around Jake Peralta (Andy Samberg), an immature but talented NYPD detective in Brooklyn\'s fictional 99th Precinct, who often comes into conflict with his new commanding officer, the serious and stern Captain Raymond Holt (Andre Braugher). The rest of the cast features Stephanie Beatriz as Rosa Diaz, Terry Crews as Terry Jeffords, Melissa Fumero as Amy Santiago, Joe Lo Truglio as Charles Boyle, Chelsea Peretti as Gina Linetti, Dirk Blocker as Michael Hitchcock, and Joel McKinnon Miller as Norm Scully.', 7, 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/2020-04-23%2012-52-14.mp4?alt=media&token=ae320b0b-f116-40a6-ad54-007af746685d', 1, 0, 0, NULL),
(5, 'GodFather', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/gf.jpg?alt=media&token=f7975032-60f3-4380-836f-a9fa1adbcff6', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/godfather_cover.jpg?alt=media&token=0a97da45-c46c-414f-b5b9-0888fdc40177', 'The Godfather is a 1972 American crime film directed by Francis Ford Coppola who co-wrote the screenplay with Mario Puzo, based on Puzo\'s best-selling 1969 novel of the same name. It is the first installment in The Godfather trilogy.', 8, 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/GodFather.mp4?alt=media&token=2b394913-55d9-47d2-b74d-054418a0a89b', 0, 0, 0, NULL),
(6, 'flash', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/flash.jpg?alt=media&token=3b42932e-d2ba-4d3e-a56b-3c2ec91042f7', 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/flash_cover.jpg?alt=media&token=8ac45f09-a4c3-42ee-aec9-7099beee89f2', 'The Flash is an American superhero television series developed by Greg Berlanti, Andrew Kreisberg, and Geoff Johns, airing on The CW. It is based on the DC Comics character Barry Allen / Flash, a costumed superhero crime-fighter with the power to move at superhuman speeds.', 8, 'https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/Flash.mp4?alt=media&token=ac805b10-1d69-4983-a211-6b8bd1844e35', 0, 1, 2, 'HELLO ME');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `userName` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `Email` text NOT NULL,
  `avatar` int(255) NOT NULL DEFAULT '700036'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `userName`, `password`, `Email`, `avatar`) VALUES
(36, '', 'd41d8cd98f00b204e9800998ecf8427e', '', 700036),
(37, 'b33w3', 'eb61eead90e3b899c6bcbe27ac581660', 'Hello@gello.com', 2131099741),
(38, 'elkhatri', 'b373870b9139bbade83396a49b1afc9a', 'hamza', 700036),
(39, 'jhdbehde', '23a5e8547ccdb8dab2643cee173af771', 'duhdeuhd', 700036);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`userName`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;


--
-- Métadonnées
--
USE `phpmyadmin`;

--
-- Métadonnées pour la table movie
--

--
-- Déchargement des données de la table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'android', 'movie', '{\"sorted_col\":\"`original` ASC\",\"CREATE_TIME\":\"2020-06-21 21:45:53\",\"col_order\":[0,1,2,3,4,5,6,7,8,9,10],\"col_visib\":[1,1,1,1,1,1,1,1,1,1,1]}', '2020-06-23 20:18:05');

--
-- Métadonnées pour la table user
--

--
-- Déchargement des données de la table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'android', 'user', '{\"sorted_col\":\"`user`.`avatar`  DESC\"}', '2020-05-27 01:35:09');

--
-- Métadonnées pour la base de données android
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
