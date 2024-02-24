import { React, useState, useEffect } from "react";
import "./css/Player.css";
import PropTypes from "prop-types";

const Player = ({ videoId, onNextSong }) => {
	const [playingLineWidth, setPlayingLineWidth] = useState("6.25%"); // Create a state variable for the width of the playing line
	const [currentMarkerIndex, setCurrentMarkerIndex] = useState(0);
	const markers = [12.5, 25, 43.75, 68.75, 100]; // Create an array of markers
	const timeSkipMarker = [1, 2, 3, 4, 5]; // Create an array of time markers

	useEffect(() => {
		// Load the IFrame Player API code asynchronously.
		var tag = document.createElement("script");
		tag.src = "https://www.youtube.com/iframe_api";
		var firstScriptTag = document.getElementsByTagName("script")[0];
		firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

		// Create the 'ytplayer' element dynamically.
		var ytplayer = document.createElement("div");
		ytplayer.id = "ytplayer";
		document.body.appendChild(ytplayer);

		// Replace the 'ytplayer' element with an <iframe> and
		// YouTube player after the API code downloads.
		window.onYouTubeIframeAPIReady = function () {
			window.player = new window.YT.Player("ytplayer", {
				height: "0",
				width: "0",
				videoId: videoId,
			});
		};
	}, []);

	useEffect(() => {
		onStopClick();
		if (window.player) {
			window.player.destroy();
			window.player = new window.YT.Player("ytplayer", {
				height: "0",
				width: "0",
				videoId: videoId,
			});
		}
	}, [videoId]);

	function onNextClick() {
		// Set the width of the playing line to 0%
		onStopClick();
		setPlayingLineWidth("6.25%");
		setCurrentMarkerIndex(0);

		if (onNextSong) {
			onNextSong();
		}

		// TODO: Reset all.
	}

	function onSkipClick() {
		// increase the width of the playing line to the next marker point
		if (currentMarkerIndex < markers.length) {
			setPlayingLineWidth(markers[currentMarkerIndex] + "%");
			setCurrentMarkerIndex((prevIndex) => prevIndex + 1);
		}
		onStopClick();
	}

	let intervalId;

	function onPlayClick() {
		if (window.player && window.player.playVideo) {
			window.player.playVideo();

			const timeMarker = [1, 2, 4, 7, 11, 16]; // Create an array of time markers

			const time =
				currentMarkerIndex < timeMarker.length
					? timeMarker[currentMarkerIndex]
					: 16;

			intervalId = setInterval(() => {
				if (window.player && window.player.getCurrentTime) {
					const currentTime = window.player.getCurrentTime();

					if (currentTime >= time) {
						if (window.player && window.player.pauseVideo) {
							console.log(window.player.getCurrentTime());
							window.player.stopVideo();
						}

						clearInterval(intervalId);
					}
				}
			}, 500); // Check every 200ms, 500ms or 1000ms?
		}
	}

	function onStopClick() {
		if (window.player && window.player.stopVideo) {
			window.player.stopVideo();
		}
		clearInterval(intervalId);
	}

	return (
		<div className="game-container">
			<h2 className="game-title">Guess the Song</h2>
			<p className="game-description">
				Listen to the music and guess the song title.
			</p>
			<div className="game-playing-line-container">
				<h3 className="game-playing-line-title">Playing Line</h3>
				<div className="game-playing-line">
					<div className="game-playing-line-markers">
						{/* Marker divs */}
						<div className="marker" style={{ left: "6.25%" }}></div>
						<div className="marker" style={{ left: "12.5%" }}></div>
						<div className="marker" style={{ left: "25%" }}></div>
						<div
							className="marker"
							style={{ left: "43.75%" }}
						></div>
						<div
							className="marker"
							style={{ left: "68.75%" }}
						></div>
						<div className="marker" style={{ left: "100%" }}></div>
					</div>
					<div
						id="playing-line"
						className="game-playing-line-progress"
						style={{ width: playingLineWidth }} // Set the width of the playing line using the state variable
					></div>
				</div>
			</div>
			<div className="game-controls">
				<button
					className="game-button game-button-skip"
					onClick={onSkipClick}
				>
					Skip (
					{currentMarkerIndex < markers.length
						? timeSkipMarker[currentMarkerIndex]
						: "to Next"}
					)
				</button>
				<button
					className="game-button game-button-play"
					onClick={onPlayClick}
				>
					Play
				</button>
				<button
					className="game-button game-button-stop"
					onClick={onStopClick}
				>
					Stop
				</button>
				<button
					className="game-button game-button-next"
					onClick={onNextClick}
				>
					Next Song
				</button>
			</div>
			<div className="game-guess-input">
				<label htmlFor="guess" className="game-guess-label">
					Your guess
				</label>
				<input
					className="game-guess-field"
					id="guess"
					type="text"
					placeholder="Your guess"
				/>
			</div>
			<button className="game-button game-button-submit">
				Submit Guess
			</button>
			<div className="game-guesses">
				<h3 className="game-guesses-title">Your Guesses</h3>
				<ul id="guesses" className="game-guesses-list">
					{/* Guess list items */}
				</ul>
			</div>
			<div id="ytplayer"></div>
		</div>
	);
};

Player.propTypes = {
	videoId: PropTypes.string.isRequired,
	onNextSong: PropTypes.func,
};

export default Player;
