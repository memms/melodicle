import React, { useState } from "react"; // Import useState from 'react'
import Player from "../components/Player";

function Home() {
	let videoIDs = ["UcsSdIXHCWM", "vt0i6nuqNEo", "Iq8h3GEe22o", "4D89Qr5vH6U"];
	const [currentVideoIndex, setCurrentVideoIndex] = useState(0);
	const handleNextSong = () => {
		setCurrentVideoIndex((prevIndex) => prevIndex + 1);
	};
	return (
		<div
			style={{
				backgroundColor: "black",
				height: "100vh",
				width: "100vw",
				position: "fixed",
			}}
		>
			<Player
				videoId={videoIDs[currentVideoIndex]}
				onNextSong={handleNextSong}
			/>
		</div>
	);
}

export default Home;
