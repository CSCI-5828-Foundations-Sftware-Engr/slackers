<#-- @ftlvariable name="playerGameOptions" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            Create Session
        </h3>
        <hr />
        <div>
            <form action="/sessions" method="post">
                <p>
                    <label>sessionName</label>
                    <input type="text" name="sessionName">
                </p>

                <h1>Choose Players</h1>
                <#list playerGameOptions.players as player>
                    <label>${player.name}
                        <input name="${player.name}" type="checkbox">
                        <span class="checkmark"></span>
                    </label>
                </#list>

                <h1>Choose Games</h1>
                <#list playerGameOptions.games as game>
                    <label>${game.name}
                        <input type="radio" name="game" value=${game.id}>
                    </label>
                </#list>

                <p>
                    <input type="submit">
                </p>
            </form>
        </div>
    </div>
</@layout.header>