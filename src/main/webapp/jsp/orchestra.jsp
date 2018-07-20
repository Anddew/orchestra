<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/orchestra.js"></script>
<head>
    <title>Orchestra</title>
</head>
<body>
    <div>
        <div>
            <h2>
                Register or remove robot...
            </h2>
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
        <div>
            <button type="submit" class="jsRemoveButton">
                Remove
            </button>
        </div>
        <div>
            Free robots:
            <span class="jsFreeRobotList">
            </span>
        </div>
    </div>

    <div>
        <div>
            <h2>
                Create task to play song...
            </h2>
        </div>
        <input class="jsSongTitle" type="text" placeholder="Songs title..."/>
        <div>
            <button type="submit" class="jsPlaySongButton">
                Play song
            </button>
            <button type="submit" class="jsBroadcastButton">
                Broadcast
            </button>
        </div>
        <div>
            Available songs:
            <span class="jsSongsList">
            </span>
        </div>
    </div>


    <div>
        <div>
            <h2>
                Create new song...
            </h2>
        </div>
        <div>
            <input class="jsNewSongArtist" type="text" placeholder="Artist name"/>
            <input class="jsNewSongTitle" type="text" placeholder="Song title"/>
        </div>
        <div>
            <input class="jsNewSongDuration" type="text" placeholder="Duration"/>
            <textarea class="jsNewSongText" placeholder="Text..."></textarea>
        </div>
        <div>
            <button type="submit" class="jsNewSongsButton">
                Create
            </button>
        </div>
    </div>


    <div class="jsHistory">
    </div>
</body>
</html>
