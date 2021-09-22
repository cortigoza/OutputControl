-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 21-09-2021 a las 06:04:28
-- Versión del servidor: 10.3.31-MariaDB-0ubuntu0.20.04.1
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `control_output`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `output`
--

CREATE TABLE `output` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `output`
--

INSERT INTO `output` (`id`, `id_user`, `id_student`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 3, 4),
(5, 5, 5),
(6, 5, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rails`
--

CREATE TABLE `rails` (
  `id` int(11) NOT NULL,
  `rail` enum('1','2','3') NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `section` enum('primaria','bachillerato','pre-escolar','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `students`
--

INSERT INTO `students` (`id`, `name`, `section`) VALUES
(1, 'luis', 'bachillerato'),
(2, 'miguel', 'primaria'),
(3, 'isabel', 'pre-escolar'),
(4, 'alejandro', 'pre-escolar'),
(6, 'lisa', 'primaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `identification` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `identification`, `phone`, `mail`, `pass`) VALUES
(1, 'luis', '105254878', '2322515', 'ejemplo@ejemplo.com', 'test'),
(2, 'fernando', '4052548', '3154647656', 'cortigoza@estudiante.uniajc.edu.co', 'prueba'),
(3, 'mauricio', '11111', '3154647656', 'mauricio@yopmail.com', 'prueba'),
(4, 'danilo', '9052577', '3154647656', 'danilo@yopmail.com', 'test'),
(5, 'isabel', '6052548', '3154644', 'isabel@yopmail.com', '12345');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `output`
--
ALTER TABLE `output`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rails`
--
ALTER TABLE `rails`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `output`
--
ALTER TABLE `output`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `rails`
--
ALTER TABLE `rails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
