<#-- @ftlvariable name="users" type="kotlin.collections.List<com.example.models.Users>" -->
<#import "_layout.ftl" as layout />
<@layout.header>



    <div class="w-full">
        <div class="mb-4">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="name">
            This is the temporary home page of Goodboards - "Find your next favorite board game here."
          </label>
        </div>
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