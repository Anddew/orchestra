<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="../resources/js/lib/jquery-3.3.1.js"></script>
<script src="../resources/js/orchestra.js"></script>
<head>
    <title>Orchestra</title>
</head>
<body>
    <div>
        <div>
            Register or release robot...
        </div>
        <input class="jsRobotName" type="text" placeholder="Robot name..."/>
        <select class="jsRobotType">
            <option value="VOCAL">Vocal</option>
            <option value="GUITAR">Guitar</option>
            <option value="BASS">Bass</option>
            <option value="DRUMS">Drummer</option>
            <option value="BRASS">Brass</option>
        </select>
        <button type="submit" class="jsRegisterButton">
            Register
        </button>
    </div>
    <div>
        <button type="submit" class="jsReleaseButton">
            Release
        </button>
    </div>

    <div>
        <div>
            Create task to play song...
        </div>
        <input class="jsSongTitle" type="text" placeholder="Songs title..."/>
        <button class="jsShowSongsButton" type="submit">
            Show songs
        </button>
        <span class="jsSongsList">
            songs list
        </span>
    </div>
    <div>
        <button type="submit" class="jsPlaySongButton">
            Play song
        </button>
    </div>

    <div>
        <div>
            Create new song...
        </div>
        <input class="jsNewSongArtist" type="text" placeholder="Artist name"/>
        <input class="jsNewSongTitle" type="text" placeholder="Song title"/>
        <input class="jsNewSongDuration" type="text" placeholder="Duration"/>
        <textarea class="jsNewSongText" placeholder="Text..."></textarea>
    </div>
    <div>
        <button type="submit" class="jsNewSongsButton">
            Create
        </button>
    </div>

    <div class="jsLog">
    </div>
</body>
</html>
