-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13-Maio-2024 às 21:07
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `vita_bloom`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id_produto` bigint(20) NOT NULL,
  `descricao_produto` varchar(255) DEFAULT NULL,
  `nome_produto` varchar(255) DEFAULT NULL,
  `valor_produto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_produto`, `descricao_produto`, `nome_produto`, `valor_produto`) VALUES
(1, 'Apresentando \'Essência Uva Celestial\': uma fragrância que captura toda a exuberância e frescor das vinhas exuberantes', 'Creme de Uva Divino', 59.99),
(2, 'Cada borrifo de \'Essência Uva Celestial\' é uma celebração da natureza e da abundância, evocando uma sensação de frescor e vitalidade. Seu aroma encantador é suave o suficiente para ser usado diariamente.', 'Essência Uva Celestial', 249.99),
(3, 'Nutre profundamente a pele com a suavidade e riqueza do abacate, deixando-a radiante e hidratada. Este creme proporciona uma sensação luxuosa e revitalizante.', 'Avocado Glow', 98.99),
(4, 'Com sua fragrância refrescante e propriedades antioxidantes, este creme de abacaxi energiza e revitaliza a pele, deixando-a com um brilho saudável e radiante.', 'Pineapple Delight', 44.98),
(5, 'Esta loção leve e refrescante de abacaxi é absorvida rapidamente pela pele, proporcionando hidratação duradoura e um aroma deliciosamente tropical.', 'Tropical Breeze', 29.99),
(6, 'Enriquecido com extratos de banana nutritivos, este hidratante deixa a pele macia, suave e deliciosamente perfumada, proporcionando uma experiência sensorial única com a Vita Bloom.', 'Banana Bliss Plus', 69.99),
(7, 'Revigore os sentidos com este hidratante de laranja, que oferece uma explosão de frescor cítrico enquanto hidrata e suaviza a pele, deixando-a com uma luminosidade natural.', 'Citrus Burst Vita', 45.98),
(8, 'Este creme luxuoso de laranja é formulado para nutrir e rejuvenescer a pele, deixando-a com um brilho radiante e uma fragrância deliciosamente revigorante.', 'Orange Radiance', 29.99),
(9, 'Uma fragrância estimulante e revigorante que captura a essência vibrante das laranjas maduras ao nascer do sol, deixando um rastro de frescor e vitalidade por onde passa.', 'Sunrise Citrus', 129.99),
(10, 'Refresque sua pele com este creme de maçã verde, que oferece hidratação intensa e um aroma revigorante, deixando-a com uma aparência e sensação revitalizadas.', 'Green Apple Revive', 249.99),
(11, 'Mime sua pele com este hidratante de morango, que proporciona nutrição intensa e um aroma doce e irresistível de morangos frescos, deixando-a suave e sedosa ao toque.', 'Strawberry Kiss', 59.99),
(12, 'Limpe e revitalize seu cabelo com este shampoo de morango, que oferece uma explosão de frescor frutado enquanto limpa suavemente e deixa os fios com um brilho saudável.', 'Berry Freshness', 129.99);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id_produto`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id_produto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
