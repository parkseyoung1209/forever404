document.addEventListener("DOMContentLoaded", function() {
	const prevButton = document.querySelector("#prevBtn");
	const nextButton = document.querySelector("#nextBtn");
	const items = document.querySelectorAll(".carousel-item");
	let currentIndex = 1;

	prevButton.addEventListener("click", function() {
		if (currentIndex > 1) {
			$(`.carousel-item:nth-child(${currentIndex - 1})`).css({ display: 'block' });
			$(`.carousel-item:nth-child(${currentIndex})`).css({ display: 'none' });
			currentIndex--;
		}
	});

	nextButton.addEventListener("click", function() {
		if (currentIndex < items.length) {
			$(`.carousel-item:nth-child(${currentIndex})`).css({ display: 'none' });
			$(`.carousel-item:nth-child(${currentIndex + 1})`).css({ display: 'block' });
			currentIndex++;
		}
	});
});