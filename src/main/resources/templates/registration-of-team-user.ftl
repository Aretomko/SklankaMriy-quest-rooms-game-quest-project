<!DOCTYPE html>
<html lang="en">
   <title>sklq</title>
   <meta charset="UTF-8">
   <link rel="icon" href="http://sklquest.com/static/uploads/icon.png">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <script src='https://kit.fontawesome.com/a076d05399.js'></script>
   <style>
     body,
     h1,
     h2,
     h3,
     h4,
     h5 {
       font-family: "Raleway", sans-serif
     }
     @media (max-width: 500px){.w3-black{font-size:10px;}}
     input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}
::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: #ff9966;
}
   </style>
<body class="w3" style="font-size:18px; background-color: black;">

<nav>
<div class="w3-top">
    <div class="w3-row w3-padding w3-black" style="padding-right:0px!important;padding-left:0px!important;  @media (max-width: 500px){font-size: 12px;}; background-color: white !important;">
      <div class="w3-col s4">
        <a href="/" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important">Квести</a>
      </div>
      <div class="w3-col s4">
        <a href="/aboutUs" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important">Про нас</a>
      </div>
      <div class="w3-col s4">
        <a href="/contacts" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important;">Контакти</a>
      </div>
    </div>
  </div>
</nav>
  <div class="w3-content" style="max-width:1400px;" id="mobile">
    <header class="w3-container w3-center w3-padding-32" style="padding-top: 80px!important; color: white;">
      <div>
         <h1><img src="/static/uploads/logo.png"  style="height:150px; width:100%; max-width: 350px; margin-left: -5px;"/></h1>
         <b>Квести для дітей, підлітків та їх батьків<span class="w3-tag"></span></b>
      </div>
      <b>Реєстрація команди на квест<span class="w3-tag"></span></b>
    </header>
    <!-- start of the different content//////////////////////////////-->
    <!-- Grid -->
    <div class="w3-row">
      <!-- Blog entries -->
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
          <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; margin-top: 58px !important; padding: 20px ; border-radius: 4px;">
            <div>
              <form action="/addNewTeamUser/${questId}" method="post">
              <#if teamNameError??>
                <label for="fname" style="color: red;">${teamNameError}</label>
                <input type="text" id="fname" name="teamName">
              <#else>
                <label for="fname">Назва команди</label>
                <input type="text" id="fname" name="teamName" <#if teamNameErrorData??> value="${teamNameErrorData}" </#if>>
              </#if>
              <#if capNameError??>
                <label for="lname" style="color: red;">${capNameError}</label>
                <input type="text" id="lname" name="capName">
              <#else>
                <label for="lname">Ім'я капітана</label>
                <input type="text" id="lname" name="capName" <#if capNameErrorData??> value="${capNameErrorData}" </#if> >
              </#if>
              <#if capNumberError??>
                <label for="lname" style="color: red;">${capNumberError}</label>
                <input type="text" id="lname" name="capNumber" <#if capNumberErrorData??> value="${capNumberErrorData}" </#if>>
              <#else>
                <label for="lname">Контактний номер капітана</label>
                <input type="text" id="lname" name="capNumber" placeholder="(000) 000-00-00" >
              </#if>
              <#if secondCapNameError??>
                <label for="lname" style="color: red;">${secondCapNameError}</label>
                <input type="text" id="lname" name="secondCapName">
              <#else>
                <label for="lname">Ім'я додаткової контактної особи</label>
                <input type="text" id="lname" name="secondCapName" <#if secondCapNameErrorData??> value="${secondCapNameErrorData}" </#if>>
              </#if>
              <#if secondCapNumberError??>
                <label for="lname" style="color: red;">${secondCapNumberError}</label>
                <input type="text" id="lname" name="secondCapNumber" <#if secondCapNumberErrorData??>  value="${secondCapNumberErrorData}" </#if>>
              <#else>
                <label for="lname">Номер телефону додаткової контактної особи</label>
                <input type="text" id="lname" name="secondCapNumber"  placeholder="(000) 000-00-00" />
              </#if>
              <#if quantityOfPlayersError??>
                <label for="lname" style="color: red;">${quantityOfPlayersError}</label>
                <input type="text" id="lname" name="quantityOfPlayers">
              <#else>
                <label for="lname">Кількість гравців</label>
                <input type="text" id="lname" name="quantityOfPlayers" <#if quantityOfPlayersErrorData??> value="${quantityOfPlayersErrorData}" </#if>>
              </#if>

                <p><button type="submit" class="w3-button w3-padding-large w3-white w3-border"><b>Зареєструватися »</b></button></p>
              </form>
            </div>
          </div>
        <hr>
        <!-- END BLOG ENTRIES -->
      </div>
      <!-- Introduction menu -->


      <!-- END GRID -->
   </div><br>
<footer class="w3-content w3-padding-64 w3-text-grey w3-xlarge" style="max-width:100%">
    <div style="margin:20px">
                <a href="https://www.facebook.com/sklyanka.mriy"><i class="fab fa-facebook w3-hover-opacity"></i></a>
                <a href="https://www.instagram.com/sklyanka_mriy"><i class="fab fa-instagram w3-hover-opacity"></i></a>
                <a href="viber://add?number=3800674656763"><i class="fab fa-viber w3-hover-opacity"></i></a>
                <a href="https://t.me/sklquest"><i class="fab fa-telegram w3-hover-opacity"></i></a>
    </div>
</footer>

    <!-- END w3-content -->
  </div>
</body>
</html>
