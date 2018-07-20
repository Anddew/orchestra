$(document).ready(function () {

    var ELEMENTS = {
        HISTORY: '.jsHistory',
        ROBOT_NAME: '.jsRobotName',
        ROBOT_TYPE: '.jsRobotType',
        REGISTER_BUTTON: '.jsRegisterButton',
        RELEASE_BUTTON: '.jsReleaseButton',
        SONG_TITLE: '.jsSongTitle',
        PLAY_SONG_BUTTON: '.jsPlaySongButton',
        SONGS_LIST: '.jsSongsList',
        SHOW_SONGS_BUTTON: '.jsShowSongsButton',
        NEW_SONG_BUTTON: '.jsNewSongsButton',
        NEW_SONG_ARTIST: '.jsNewSongArtist',
        NEW_SONG_TITLE: '.jsNewSongTitle',
        NEW_SONG_DURATION: '.jsNewSongDuration',
        NEW_SONG_TEXT: '.jsNewSongText'
    };

    var
        $history = $(ELEMENTS.HISTORY),
        $robotName = $(ELEMENTS.ROBOT_NAME),
        $robotType = $(ELEMENTS.ROBOT_TYPE),
        $registerButton = $(ELEMENTS.REGISTER_BUTTON),
        $releaseButton = $(ELEMENTS.RELEASE_BUTTON),
        $songTitle = $(ELEMENTS.SONG_TITLE),
        $playSongButton = $(ELEMENTS.PLAY_SONG_BUTTON),
        $songsList = $(ELEMENTS.SONGS_LIST),
        $showSongsButton = $(ELEMENTS.SHOW_SONGS_BUTTON),
        $newSongButton = $(ELEMENTS.NEW_SONG_BUTTON),
        $newSongArtist = $(ELEMENTS.NEW_SONG_ARTIST),
        $newSongTitle = $(ELEMENTS.NEW_SONG_TITLE),
        $newSongDuration = $(ELEMENTS.NEW_SONG_DURATION),
        $newSongText = $(ELEMENTS.NEW_SONG_TEXT)
    ;

    setInterval(getUpdates, 1000);

    function getUpdates() {
        $.ajax({
            url: 'orchestra/update',
            type: 'GET',
            contentType: "application/json",
            statusCode: {
              200: function (elements) {
                  $history.empty();
                  for(var i = (Object.keys(elements).length - 1); i >= 0 ; i--){
                      $history.append($("<div></div>").text(elements[i]));
                  }
              }
            }
        });
    }

    $registerButton.on("click", function () {
        $.ajax({
            url: 'robot/register',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                name: $robotName.val(),
                type: $robotType.val()
            }),
            statusCode: {
                400: function () {
                    alert("Error!")
                }
            }
        });
    });

    $releaseButton.on("click", function () {
        $.ajax({
            url: 'robot/release',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                name: $robotName.val()
            }),
            statusCode: {
                400: function () {
                    alert("Error!")
                }
            }
        });
    });

    $playSongButton.on("click", function () {
        $.ajax({
            url: 'robot/play',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                name: $robotName.val(),
                title: $songTitle.val()
            }),
            statusCode: {
                400: function () {
                    alert("Error!")
                }
            }
        });
    });

    $showSongsButton.on("click", function () {
        $.ajax({
            url: 'song',
            type: 'GET',
            contentType: "application/json",
            statusCode: {
                200: function (songs) {
                    $songsList.empty();
                    for(var i = 0; i < songs.length; i++) {
                        $songsList.append("'").append(songs[i]).append("' ");
                    }
                },
                400: function () {
                    alert("Error!")
                }
            }
        });
    });

    $newSongButton.on("click", function () {
        $.ajax({
            url: 'song',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                artist: $newSongArtist.val(),
                title: $newSongTitle.val(),
                duration: $newSongDuration.val(),
                text: $newSongText.val()
            }),
            statusCode: {
                400: function () {
                    alert("Error!")
                }
            }
        });
    });

});