<#-- @ftlvariable name="users" type="kotlin.collections.List<com.example.models.Users>" -->
<#import "_layout.ftl" as layout />
<@layout.header>



    <div class="w-full">
      <form action="/form" method="post"  class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div class="mb-4">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="name">
            What is your name?
          </label>
          <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" name="title" id="name" type="text" placeholder="Name">
          <p>
              <input class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 mt-3 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
          </p>
        </div>
      </form>
    </div>


    <div class="w-full flex items-center justify-center">
        <table class="table-auto border my-3">
          <thead>
            <tr>
              <th class="font-bold p-2 border-b text-left">This is the super-exclusive list of Goodboarders</th>
            </tr>
          </thead>
          <tbody>
            <#list users?reverse as user>
                <tr>
                    <td class="p-2 border-b text-left"><a href="/form"  class="font-medium text-blue-600 dark:text-blue-500 hover:underline">${user.title}</a></td>
                </tr>
            </#list>
          </tbody>
        </table>
    </div>






</@layout.header>