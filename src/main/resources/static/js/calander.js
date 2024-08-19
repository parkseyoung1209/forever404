document.addEventListener("DOMContentLoaded", function () {
  const Calendar = FullCalendar.Calendar;

  const calendarEl = document.getElementById("calendar");

  const calendar = new Calendar(calendarEl, {
    headerToolbar: {
      left: "prev,next today",
      center: "title",
      right: "dayGridMonth,timeGridWeek,timeGridDay",
    },
    selectable: true,
    editable: true,
    dateClick: function (info) {
      const month = calendarEl
        .querySelector(".fc-toolbar-title")
        .innerText.split(" ")[0];
      const clickedDate = info.dateStr;
      const selectedMonth = info.date.getMonth(); // 0부터 시작하는 월 인덱스
      // 예: 7월 (8월의 경우 7로 설정)
      if (selectedMonth === 0 && month === "January") {
        showModal(clickedDate);
      } else if (selectedMonth === 1 && month === "February") {
        showModal(clickedDate);
      } else if (selectedMonth === 2 && month === "March") {
        showModal(clickedDate);
      } else if (selectedMonth === 3 && month === "April") {
        showModal(clickedDate);
      } else if (selectedMonth === 4 && month === "May") {
        showModal(clickedDate);
      } else if (selectedMonth === 5 && month === "June") {
        showModal(clickedDate);
      } else if (selectedMonth === 6 && month === "July") {
        showModal(clickedDate);
      } else if (selectedMonth === 7 && month === "August") {
        showModal(clickedDate);
      } else if (selectedMonth === 8 && month === "September") {
        showModal(clickedDate);
      } else if (selectedMonth === 9 && month === "October") {
        showModal(clickedDate);
      } else if (selectedMonth === 10 && month === "November") {
        showModal(clickedDate);
      } else if (selectedMonth === 11 && month === "December") {
        showModal(clickedDate);
      }
    },
  });

  calendar.render();

  function showModal() {
    const modal = document.getElementById("bigModal");
    modal.style.display = "block";
  }

  document.getElementById("X").addEventListener("click", function () {
    document.getElementById("bigModal").style.display = "none";
  });
});

$("#six").click(function () {
  $("#detModal").css("display", "block");
  $("#bigModal").css("display", "none");
});

$("#X2").click(function () {
  $("#detModal").css("display", "none");
  $("#bigModal").css("display", "block");
});


$("#groupmake").click(function () {
  $("#modal1").css("display", "block");
});

$(".close").click(function () {
  $(".modal").css("display", "none");
});

$(window).click(function (event) {
  if ($(event.target).is(".modal")) {
    $(".modal").css("display", "none");
  }
});

$(document).keydown(function (event) {
  if (event.keyCode == 27) {
    $(".modal").css("display", "none");
  }
});

$("#addgroup").mouseover((e) => {
  setTimeout(() => {
    let content = $(".modalgroup");

    if (content.css("display") === "none") {
      content.fadeIn(450);
    }
  }, 400);
});

$("#calendar-container").mouseover((e) => {
  let content = $(".modalgroup");

  if (content.css("display") != "none") {
    content.fadeOut(200);
  }
});


$("#grouppart").click(function () {
  $("#modal2").css("display", "block");
});

$(".close").click(function () {
  $(".modal").css("display", "none");
});

$(window).click(function (event) {
  if ($(event.target).is(".modal")) {
    $(".modal").css("display", "none");
  }
});

$(document).keydown(function (event) {
  if (event.keyCode == 27) {
    $(".modal").css("display", "none");
  }
});


$(".user").click(function () {
  $(".mymodal").css("display", "block");
});

$("#calendar-container").mouseover((e) => {
  let content = $(".mymodal");

  if (content.css("display") != "none") {
    content.fadeOut(200);
  }
});