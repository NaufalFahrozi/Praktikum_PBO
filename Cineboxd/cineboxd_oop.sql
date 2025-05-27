-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2025 at 01:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cineboxd_oop`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbakun`
--

CREATE TABLE `tbakun` (
  `idAkun` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbakun`
--

INSERT INTO `tbakun` (`idAkun`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'user', 'user', 'user'),
(3, 'bani', 'password123', 'user'),
(4, 'ricky', 'password123', 'user'),
(5, 'nabil', 'password123', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `tbfilm`
--

CREATE TABLE `tbfilm` (
  `idFilm` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `year` int(11) NOT NULL,
  `director` varchar(100) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `synopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbfilm`
--

INSERT INTO `tbfilm` (`idFilm`, `title`, `year`, `director`, `genre`, `synopsis`) VALUES
(1, 'The Shawshank Redemption', 1994, 'Frank Darabont', 'Drama', 'Cerita tentang bankir Andy Dufresne yang dituduh membunuh istrinya dan kekasihnya, meskipun dia mengklaim tidak bersalah. Di penjara Shawshank, dia berteman dengan Ellis Boyd \"Red\" Redding, dan bersama-sama mereka menemukan harapan sebagai sesuatu yang baik.'),
(2, 'The Godfather', 1972, 'Francis Ford Coppola', 'Crime', 'Don Vito Corleone, kepala keluarga mafia Corleone di New York, yang usianya semakin tua memutuskan untuk menyerahkan kendali bisnisnya kepada putra bungsunya Michael. Namun, ketika terjadi upaya pembunuhan terhadap Don, Michael yang sebelumnya ingin menjauh dari bisnis keluarganya harus terlibat.'),
(3, 'Pulp Fiction', 1994, 'Quentin Tarantino', 'Crime', 'Kisah penjahat kecil, gangster besar, dan para korbannya yang saling terhubung dalam empat cerita tentang kekerasan dan penebusan.'),
(4, 'The Dark Knight', 2008, 'Christopher Nolan', 'Action', 'Batman, Gordon dan Harvey Dent berusaha menghancurkan organisasi kriminal yang beroperasi di Gotham. Namun, mereka menghadapi masalah ketika Joker, penjahat misterius, muncul dan menciptakan kekacauan.'),
(5, 'Parasite', 2019, 'Bong Joon-ho', 'Thriller', 'Kisah keluarga Ki-taek yang pengangguran yang hidup di apartemen semi-basement kumuh. Suatu hari, anak laki-lakinya berhasil mendapatkan pekerjaan sebagai guru privat di keluarga kaya Park, membuka peluang baru bagi keluarganya.'),
(6, 'Seven Samurai', 1954, 'Akira Kurosawa', 'Action', 'Petani desa yang putus asa menyewa tujuh samurai untuk melindungi mereka dari bandit yang berulang kali menyerang dan merampok desa mereka.'),
(7, 'Inception', 2010, 'Christopher Nolan', 'Sci-Fi', 'Seorang pencuri yang mencuri rahasia korporasi melalui teknologi berbagi-mimpi ditugaskan untuk melakukan hal yang berlawanan, menanamkan ide ke dalam pikiran CEO.'),
(8, 'Interstellar', 2014, 'Christopher Nolan', 'Sci-Fi', 'Sekelompok penjelajah menempuh perjalanan melampaui batas-batas yang dikenal manusia untuk menemukan tempat baru bagi manusia di tengah krisis ketersediaan makanan di Bumi.'),
(9, 'Spirited Away', 2001, 'Hayao Miyazaki', 'Animation', 'Selama pindahan ke rumah baru, seorang gadis berumur 10 tahun memasuki dunia yang dihuni oleh dewa, penyihir, dan hantu, di mana manusia diubah menjadi binatang.'),
(10, 'The Lighthouse', 2019, 'Robert Eggers', 'Horror', 'Kisah dua penjaga mercusuar yang harus menjaga kewarasan mereka saat hidup di pulau terpencil dan misterius di lepas pantai New England pada tahun 1890-an.'),
(11, 'The Godfather: Part II', 1974, 'Francis Ford Coppola', 'Crime', 'Kisah lanjutan keluarga Corleone di bawah kepemimpinan Michael Corleone, berjalan paralel dengan kisah masa muda ayahnya, Vito Corleone, dan perjalanannya dari Sisilia ke Amerika.'),
(12, 'Back to the Future', 1985, 'Robert Zemeckis', 'Sci-Fi', 'Marty McFly, remaja SMA, secara tidak sengaja dikirim kembali ke tahun 1955 dengan mesin waktu yang dibuat oleh sahabatnya, Doc Brown. Ia harus memastikan orang tuanya bertemu dan jatuh cinta agar dia bisa kembali ke masa depan.'),
(13, 'Eternal Sunshine of the Spotless Mind', 2004, 'Michel Gondry', 'Romance', 'Setelah hubungan mereka memburuk, Joel dan Clementine menjalani prosedur untuk saling menghapus dari ingatan masing-masing. Tapi ketika Joel menjalani prosedur, dia mulai menemukan bahwa dia masih mencintainya.'),
(14, 'The Truman Show', 1998, 'Peter Weir', 'Drama', 'Truman Burbank menjalani kehidupan normal dan bahagia, sampai suatu hari dia menemukan bahwa seluruh hidupnya adalah acara realitas TV yang disiarkan 24/7 ke seluruh dunia.'),
(15, 'Whiplash', 2014, 'Damien Chazelle', 'Drama', 'Seorang drummer muda berbakat yang bertekad menjadi yang terbaik di konservatori jazz elit dilatih oleh seorang instruktur yang kejam dan perfeksionis.'),
(16, 'Blade Runner 2049', 2017, 'Denis Villeneuve', 'Sci-Fi', '30 tahun setelah kejadian di film pertama, K, blade runner baru, menemukan rahasia tersembunyi yang berpotensi menenggelamkan apa yang tersisa dari masyarakat.'),
(17, 'Casablanca', 1942, 'Michael Curtiz', 'Romance', 'Selama Perang Dunia II, seorang pemilik klub Amerika di Casablanca harus memilih antara cintanya pada wanita dari masa lalunya dan membantu suaminya, seorang pemimpin Perlawanan, melarikan diri dari Nazi.'),
(18, '12 Angry Men', 1957, 'Sidney Lumet', 'Drama', '12 anggota juri harus memutuskan nasib seorang remaja yang didakwa melakukan pembunuhan. Apa yang tampak sebagai kasus yang jelas, menjadi rumit ketika satu juri mulai mempertanyakan bukti-buktinya.'),
(19, 'Get Out', 2017, 'Jordan Peele', 'Horror', 'Seorang pria kulit hitam berkunjung ke keluarga pacarnya yang kulit putih untuk akhir pekan, namun keramahan mereka menyembunyikan kebenaran yang mengerikan.'),
(20, 'The Matrix', 1999, 'Lana Wachowski, Lilly Wachowski', 'Sci-Fi', 'Seorang hacker komputer mempelajari dari para pemberontak misterius tentang sifat sejati realitasnya dan perannya dalam perang melawan pengendalinya.');

-- --------------------------------------------------------

--
-- Table structure for table `tbreview`
--

CREATE TABLE `tbreview` (
  `idReview` int(11) NOT NULL,
  `idFilm` int(11) NOT NULL,
  `idAkun` int(11) NOT NULL,
  `rating` int(11) NOT NULL CHECK (`rating` >= 1 and `rating` <= 10),
  `komentar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbreview`
--

INSERT INTO `tbreview` (`idReview`, `idFilm`, `idAkun`, `rating`, `komentar`) VALUES
(1, 1, 2, 10, 'Film terbaik sepanjang masa! Ceritanya sangat menyentuh dan mengajarkan tentang harapan.'),
(2, 1, 3, 9, 'Akting Tim Robbins dan Morgan Freeman luar biasa. Kisah yang benar-benar membuat terharu.'),
(3, 2, 2, 10, 'Masterpiece! Film gangster terbaik yang pernah dibuat. Marlon Brando sebagai Don Corleone sangat ikonik.'),
(4, 2, 4, 8, 'Film klasik dengan alur cerita yang kompleks namun tetap menarik. Acting dan directing sangat baik.'),
(5, 3, 3, 9, 'Gaya bercerita non-linear Tarantino sangat brilian. Dialog-dialognya tajam dan menghibur.'),
(6, 3, 5, 10, 'Salah satu film terbaik dari Quentin Tarantino. Saya suka cara ceritanya dirangkai.'),
(7, 4, 2, 9, 'Heath Ledger sebagai Joker adalah performa terbaik dalam sejarah film superhero.'),
(8, 4, 5, 8, 'Christopher Nolan berhasil membuat film superhero yang dark dan realistis. Heath Ledger luar biasa!'),
(9, 5, 3, 10, 'Film yang sangat cerdas dengan kritik sosial yang tajam. Plot twist-nya mengejutkan!'),
(10, 5, 4, 10, 'Film Korea terbaik yang pernah saya tonton. Penuh kejutan dan pesan sosial yang mendalam.'),
(11, 6, 2, 9, 'Film klasik yang tetap relevan hingga saat ini. Kurosawa adalah maestro!'),
(12, 7, 3, 8, 'Konsep yang brilian dan eksekusi yang kompleks. Perlu ditonton lebih dari sekali untuk benar-benar memahaminya.'),
(13, 8, 4, 9, 'Visual yang menakjubkan dan kisah yang emotif. Hans Zimmer membuat soundtrack terbaik di sini.'),
(14, 9, 5, 10, 'Animasi terbaik dari Studio Ghibli. Penuh fantasi dan keajaiban yang memukau.'),
(15, 10, 2, 7, 'Film yang aneh tapi menarik. Suasana mencekamnya benar-benar terasa.'),
(16, 11, 2, 10, 'Sekuel yang bahkan lebih baik dari film pertamanya. De Niro sebagai Vito muda sangat brilian.'),
(17, 11, 3, 9, 'Karakter development Michael Corleone sangat dalam. Coppola benar-benar memahami apa yang dia lakukan.'),
(18, 11, 4, 8, 'Film gangster terbaik sepanjang masa. Alur cerita yang kompleks membuatnya semakin menarik.'),
(19, 12, 3, 9, 'Film klasik yang selalu menyenangkan ditonton. Michael J. Fox dan Christopher Lloyd memiliki chemistry yang luar biasa.'),
(20, 12, 4, 8, 'Konsep perjalanan waktu yang dibuat sederhana namun tetap menarik. Penuh dengan momen ikonik.'),
(21, 12, 5, 7, 'Salah satu film terbaik 80-an, tapi beberapa efek spesialnya sudah terlihat kuno sekarang.'),
(22, 13, 2, 10, 'Film yang sangat mendalam tentang cinta dan memori. Kate Winslet dan Jim Carrey luar biasa.'),
(23, 13, 4, 8, 'Ceritanya unik dan menyentuh. Charlie Kaufman memang jenius dalam menulis cerita.'),
(24, 13, 5, 6, 'Konsepnya menarik tapi kadang alurnya terlalu membingungkan untuk diikuti.'),
(25, 14, 2, 8, 'Jim Carrey menunjukkan bakat aktingnya yang luar biasa. Premisnya sangat orisinal untuk zamannya.'),
(26, 14, 3, 9, 'Sebuah kritik sosial yang dikemas dengan sangat cerdas tentang reality TV dan kebutuhan akan privasi.'),
(27, 14, 5, 7, 'Menarik dan menghibur, meski sedikit predictable di bagian akhir.'),
(28, 15, 3, 10, 'Intensitas film ini luar biasa. Hubungan guru-murid yang toxic divisualisasikan dengan sangat baik.'),
(29, 15, 4, 9, 'J.K. Simmons pantas mendapatkan Oscar. Salah satu film musik terbaik yang pernah dibuat.'),
(30, 15, 2, 8, 'Sangat intens dan menegangkan. Ending-nya sangat memuaskan meski menimbulkan pertanyaan moral.'),
(31, 16, 4, 10, 'Sekuel yang luar biasa dengan visual yang memukau. Denis Villeneuve adalah master dalam sci-fi.'),
(32, 16, 5, 7, 'Secara visual menakjubkan tapi alurnya lambat dan terlalu panjang. Butuh konsentrasi ekstra untuk mengikuti.'),
(33, 16, 3, 8, 'Eksplorasi tentang kemanusiaan dan AI yang sangat dalam. Roger Deakins pantas mendapat Oscar untuk sinematografinya.'),
(34, 17, 2, 9, 'Film klasik yang tetap relevan hingga sekarang. Bogart dan Bergman memiliki chemistry yang luar biasa.'),
(35, 17, 3, 10, 'Salah satu film terbaik sepanjang masa. Dialog-dialognya sangat ikonik dan sering dikutip.'),
(36, 17, 5, 8, 'Untuk film hitam-putih dari era 40-an, ceritanya tetap terasa segar dan mengharukan.'),
(37, 18, 4, 10, 'Film yang sangat cerdas dan intens, meski hanya berlatar satu ruangan. Henry Fonda luar biasa.'),
(38, 18, 5, 9, 'Menunjukkan bahwa film hebat tidak perlu aksi atau efek khusus. Semuanya tentang dialog dan karakter.'),
(39, 18, 2, 8, 'Kekuatan persuasi dan prejudice manusia digambarkan dengan sangat baik. Alurnya tetap menegangkan sampai akhir.'),
(40, 19, 3, 9, 'Film horor yang cerdas dengan kritik sosial yang tajam. Jordan Peele adalah sutradara yang brilian.'),
(41, 19, 5, 10, 'Menggunakan genre horor untuk menyampaikan pesan tentang rasisme dengan cara yang sangat efektif.'),
(42, 19, 4, 8, 'Plot twist-nya benar-benar tak terduga. Film horor terbaik dalam beberapa tahun terakhir.'),
(43, 20, 2, 9, 'Revolusioner untuk zamannya. Efek visual dan konsep filosofisnya sangat mempengaruhi budaya pop.'),
(44, 20, 4, 10, 'Film sci-fi yang sempurna dengan adegan action yang ikonik. Konsep \"bullet time\" mengubah industri film.'),
(45, 20, 5, 8, 'Premise dan visual yang luar biasa. Keanu Reeves sebagai Neo adalah casting yang sempurna.'),
(46, 1, 4, 8, 'Film bagus tapi menurutku sedikit terlalu lambat di beberapa bagian. Tapi memang ending-nya memuaskan.'),
(47, 1, 5, 9, 'Cerita tentang persahabatan dan pengharapan yang sangat menyentuh. Tim Robbins dan Morgan Freeman menunjukkan chemistry luar biasa.'),
(48, 2, 3, 9, 'Klasik yang tidak lekang oleh waktu. Scene pernikahan di awal film adalah salah satu pembuka terbaik dalam sejarah film.'),
(49, 2, 5, 7, 'Film bagus tapi menurutku terlalu dipuja-puja. Beberapa bagian terasa sangat lambat dan membosankan.'),
(50, 3, 2, 8, 'Gaya bercerita non-linear yang sangat segar untuk zamannya. Dialog-dialognya sangat menghibur.'),
(51, 3, 4, 7, 'Film menarik tapi kadang terlalu indulging. Beberapa adegan kekerasan terasa berlebihan dan tidak perlu.'),
(52, 4, 3, 10, 'Film superhero terbaik sepanjang masa. Heath Ledger mengubah standar peran antagonis dalam film.'),
(53, 4, 4, 7, 'Heath Ledger memang luar biasa, tapi menurutku plot-nya terlalu kompleks dan beberapa bagian tidak masuk akal.'),
(54, 5, 2, 8, 'Film Korea yang berani dengan pesan sosial yang kuat. Plot twist-nya sangat mengejutkan.'),
(55, 5, 5, 9, 'Film dengan kritik sosial yang dibalut dengan cerita yang sangat menarik. Pantas menjadi film non-Inggris pertama yang memenangkan Oscar.'),
(56, 6, 3, 8, 'Film klasik dengan pengaruh besar di dunia perfilman. Tapi durasi 3+ jam membuatnya sulit untuk dinikmati dalam sekali tonton.'),
(57, 6, 4, 10, 'Karya Kurosawa yang sempurna. Meski tua, koreografi pertarungannya tetap mengesankan hingga sekarang.'),
(58, 6, 5, 7, 'Film yang bagus dari segi sejarah perfilman, tapi terlalu panjang dan lambat untuk standar penonton modern.'),
(59, 7, 2, 10, 'Nolan selalu luar biasa dalam membuat konsep yang rumit menjadi film yang menghibur. Ending-nya membuat penasaran.'),
(60, 7, 4, 9, 'Konsep yang sangat orisinil dengan eksekusi visual yang memukau. Hans Zimmer juga memberikan soundtrack yang sempurna.'),
(61, 7, 5, 6, 'Secara visual memang menakjubkan, tapi ceritanya terlalu berusaha terlihat pintar sampai jadi membingungkan.'),
(62, 8, 2, 8, 'Visual dan soundtrack yang luar biasa. Tapi beberapa bagian terlalu bergantung pada penjelasan ilmiah yang rumit.'),
(63, 8, 3, 10, 'Perpaduan sempurna antara sci-fi, human drama, dan visual yang memukau. Salah satu film terbaik dekade ini.'),
(64, 8, 5, 7, 'Film yang ambisius tapi kadang terlalu sentimentil. Adegan di planet air dan bagian akhir agak mengecewakan.'),
(65, 9, 2, 9, 'Animasi terbaik dari Miyazaki. Dunia fantasi yang diciptakan sangat kaya dan detail.'),
(66, 9, 3, 10, 'Masterpiece dari Studio Ghibli. Setiap frame seperti lukisan yang bergerak.'),
(67, 9, 4, 8, 'Film animasi yang sangat indah, meski ceritanya kadang terlalu abstrak untuk sepenuhnya dipahami.'),
(68, 10, 3, 6, 'Terlalu artistik dan abstrak. Sulit diikuti dan terasa seperti berusaha terlalu keras untuk jadi film cult.'),
(69, 10, 4, 8, 'Film psikologis yang gelap dan intens. Akting Willem Dafoe dan Robert Pattinson sangat mengesankan.'),
(70, 10, 5, 5, 'Visual hitam-putihnya memang bagus, tapi alurnya membosankan dan terlalu ambigu. Tidak seperti yang diharapkan.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbakun`
--
ALTER TABLE `tbakun`
  ADD PRIMARY KEY (`idAkun`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `tbfilm`
--
ALTER TABLE `tbfilm`
  ADD PRIMARY KEY (`idFilm`),
  ADD UNIQUE KEY `unique_film` (`title`,`year`);

--
-- Indexes for table `tbreview`
--
ALTER TABLE `tbreview`
  ADD PRIMARY KEY (`idReview`),
  ADD UNIQUE KEY `unique_review` (`idFilm`,`idAkun`),
  ADD KEY `idAkun` (`idAkun`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbakun`
--
ALTER TABLE `tbakun`
  MODIFY `idAkun` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbfilm`
--
ALTER TABLE `tbfilm`
  MODIFY `idFilm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tbreview`
--
ALTER TABLE `tbreview`
  MODIFY `idReview` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbreview`
--
ALTER TABLE `tbreview`
  ADD CONSTRAINT `tbreview_ibfk_1` FOREIGN KEY (`idFilm`) REFERENCES `tbfilm` (`idFilm`) ON DELETE CASCADE,
  ADD CONSTRAINT `tbreview_ibfk_2` FOREIGN KEY (`idAkun`) REFERENCES `tbakun` (`idAkun`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
