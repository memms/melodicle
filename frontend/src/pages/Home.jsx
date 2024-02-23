import React, { useState } from "react"; // Import useState from 'react'
import Player from "../components/Player";

function Home() {
	return (
		<div
			style={{
				backgroundColor: "black",
				height: "100vh",
				width: "100vw",
				position: "fixed",
			}}
		>
			<Player />
		</div>
	);
}

export default Home;
