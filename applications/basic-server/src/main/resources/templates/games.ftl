<#-- @ftlvariable name="games" -->
<#import "_layout.ftl" as layout />
<@layout.header>

<div class="grid grid-cols-2 gap-4 h-[100%]">
    <#list games?reverse as game>
    <div class="w-full flex items-center justify-center">
        <a class="font-medium text-blue-600 dark:text-blue-500 hover:underline" href="/game/${game.id}">
        <div
          class="flex flex-col rounded-lg bg-[#cdc9cb] shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] m-5 p-3 dark:bg-neutral-700 md:max-w-xl md:flex-row">

              <img
                class="h-40 w-40 rounded-t-lg object-cover"
                src="https://picsum.photos/200"
                alt="" />
              <div class="game flex flex-col justify-start p-6">
                <h5
                  class="mb-2 text-xl font-medium text-neutral-800 dark:text-neutral-50">
                  <a class="game-name font-medium text-[#ce5936] dark:text-blue-500 hover:underline" href="/game/${game.id}">Game: ${game.name} </a>
                </h5>
                <p class="game-description mb-4 text-base text-neutral-600 dark:text-neutral-200">
                  ${game.description}
                </p>
                <p class="game-details text-xs text-neutral-500 dark:text-neutral-300">
                  See details
                </p>
              </div>
          </a>
        </div>
    </div>
    </#list>
</div>
<div class="flex flex-col items-center">
    <button class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded">
      Load More
    </button>
</div>








</@layout.header>