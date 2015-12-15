<!-- Created by iuliana.cosmina on 7/09/15. -->

<h2>
    [Mock] New Person
</h2>

<div class="person">

    <form id="newPersonForm" method="POST" >
        <table>
            <tr>
                <th>
                    First Name
                </th>
                <td><input path="firstName"/></td>
            </tr>
            <tr>
                <th>
                    Middle Name
                </th>
                <td><input path="middleName"/></td>
            </tr>
            <tr>
                <th>
                    Last Name
                </th>
                <td><input path="lastName"/></td>
            </tr>
            <tr>
                <th>
                    Date of Birth
                </th>
                <td><input path="dateOfBirth"/></td>
            </tr>

            <tr>
                <th>
                   Gender
                </th>
                <td>
                    <input type="radio" name="sex" value="male" checked>Male
                    <br>
                    <input type="radio" name="sex" value="female">Female
                </td>
            </tr>
            <tr>
                <th>
                   Hospital
                </th>
                <td>
                    <select>
                        <option value="hospital1">Saint Helen General Hospital</option>
                        <option value="hospital2">Salerno Children Hospital</option>
                        <option value="hospital3">Seattle General Hospital</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- TODO 32. Add what is missing for this button in order for the transition  to be done to the next view state-->
                    <button type="submit">
                        Proceed
                    </button>
                </td>
            </tr>
        </table>

    </form>

</div>
