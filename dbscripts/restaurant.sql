-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 29 2019 г., 16:24
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `restaurant`
--

-- --------------------------------------------------------

--
-- Структура таблицы `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `items`
--

INSERT INTO `items` (`ID`, `name`, `description`, `cost`) VALUES
(1, 'Borsh', 'Grandmamy soup', 150),
(2, 'Vodka and GrandFather SAMOGHON', 'Bad drink', 500.12),
(3, 'Grenki', 'fried crackers', 120),
(4, 'Beer', 'it''s alive', 50);

-- --------------------------------------------------------

--
-- Структура таблицы `items_orders`
--

CREATE TABLE IF NOT EXISTS `items_orders` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `orders_id` int(10) NOT NULL,
  `items_dictionary_id` int(10) NOT NULL,
  `quantity` int(2) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `orders_id` (`orders_id`),
  KEY `items_dictionary_id` (`items_dictionary_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `items_orders`
--

INSERT INTO `items_orders` (`ID`, `orders_id`, `items_dictionary_id`, `quantity`) VALUES
(1, 1, 1, 2),
(2, 2, 2, 1),
(3, 3, 3, 15),
(4, 4, 4, 12);

-- --------------------------------------------------------

--
-- Структура таблицы `officiant`
--

CREATE TABLE IF NOT EXISTS `officiant` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Second_Name` varchar(50) NOT NULL,
  `First_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `officiant`
--

INSERT INTO `officiant` (`ID`, `Second_Name`, `First_Name`) VALUES
(1, 'Sidorov', 'Inqvar'),
(2, 'Ivanov', 'Petr'),
(3, 'Sidorov', 'Ivan'),
(4, 'Ivanov', 'Ivar');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `officiant_id` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `officiant_id` (`officiant_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`ID`, `Date`, `officiant_id`) VALUES
(1, '2007-05-11', 1),
(2, '2007-07-11', 3),
(3, '2007-05-11', 4),
(4, '2007-05-10', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
