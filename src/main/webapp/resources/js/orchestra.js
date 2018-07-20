$(document).ready(function () {

    var ELEMENTS = {
        HISTORY: '.jsHistory',
        ROBOT_NAME: '.jsRobotName',
        ROBOT_TYPE: '.jsRobotType',
        REGISTER_BUTTON: '.jsRegisterButton',
        REMOVE_BUTTON: '.jsRemoveButton',
        SONG_TITLE: '.jsSongTitle',
        PLAY_SONG_BUTTON: '.jsPlaySongButton',
        SONGS_LIST: '.jsSongsList',
        NEW_SONG_BUTTON: '.jsNewSongsButton',
        NEW_SONG_ARTIST: '.jsNewSongArtist',
        NEW_SONG_TITLE: '.jsNewSongTitle',
        NEW_SONG_DURATION: '.jsNewSongDuration',
        NEW_SONG_TEXT: '.jsNewSongText',
        FREE_ROBOT_LIST: '.jsFreeRobotList',
        BROADCAST_BUTTON: '.jsBroadcastButton'
    };

    var
        $history = $(ELEMENTS.HISTORY),
        $robotName = $(ELEMENTS.ROBOT_NAME),
        $robotType = $(ELEMENTS.ROBOT_TYPE),
        $registerButton = $(ELEMENTS.REGISTER_BUTTON),
        $removeButton = $(ELEMENTS.REMOVE_BUTTON),
        $songTitle = $(ELEMENTS.SONG_TITLE),
        $playSongButton = $(ELEMENTS.PLAY_SONG_BUTTON),
        $songsList = $(ELEMENTS.SONGS_LIST),
        $newSongButton = $(ELEMENTS.NEW_SONG_BUTTON),
        $newSongArtist = $(ELEMENTS.NEW_SONG_ARTIST),
        $newSongTitle = $(ELEMENTS.NEW_SONG_TITLE),
        $newSongDuration = $(ELEMENTS.NEW_SONG_DURATION),
        $newSongText = $(ELEMENTS.NEW_SONG_TEXT),
        $freeRobotList = $(ELEMENTS.FREE_ROBOT_LIST),
        $broadcastButton = $(ELEMENTS.BROADCAST_BUTTON)
    ;

    setInterval(getUpdates, 1000);
    getFreeRobots();
    getAllSongs();

    function getUpdates() {
        $.ajax({
            url: 'update',
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

    function getFreeRobots() {
        $.ajax({
            url: 'robot/free',
            type: 'GET',
            contentType: "application/json",
            statusCode: {
                200: function (robots) {
                    $freeRobotList.empty();
                    for(var i = 0; i < robots.length; i++) {
                        $freeRobotList.append("'").append(robots[i].name).append(" : ").append(robots[i].type).append("' ");
                    }
                }
            }
        });
    }

    function getAllSongs() {
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
    }

    function registerRobot() {
        $.ajax({
            url: 'robot/register',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                name: $robotName.val(),
                type: $robotType.val()
            }),
            statusCode: {
                200: function () {
                    getFreeRobots()
                },
                400: function () {
                    alert("Error!")
                }
            }
        });
    }

    function removeRobot() {
        $.ajax({
            url: 'robot/remove',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                name: $robotName.val()
            }),
            statusCode: {
                200: function () {
                    getFreeRobots()
                },
                400: function () {
                    alert("Error!")
                }
            }
        });
    }

    function playSong() {
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
    }

    function broadcast() {
        $.ajax({
            url: 'robot/broadcast',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                title: $songTitle.val()
            }),
            statusCode: {
                200: function () {
                    getFreeRobots();
                },
                400: function () {
                    alert("Error!")
                }
            }
        });
    }

    function createNewSong() {
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
                200: function () {
                    getAllSongs()
                },
                400: function () {
                    alert("Error!")
                }
            }
        });
    }

    $registerButton.on("click", function () {
        registerRobot();
    });

    $removeButton.on("click", function () {
        removeRobot();
    });

    $playSongButton.on("click", function () {
        playSong();
    });

    $newSongButton.on("click", function () {
        createNewSong();
    });
    
    $broadcastButton.on("click", function () {
        broadcast();
    })

});