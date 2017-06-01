
function showRegisterForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.registerBox').fadeIn('fast');
        $('.login-footer').fadeOut('fast',function(){
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Register!');
    }); 
    $('.error').removeClass('alert alert-danger').html('');
       
}
function showLoginForm(){
    $('#loginModal .registerBox').fadeOut('fast',function(){
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast',function(){
            $('.login-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Login!');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function openLoginModal(){
    showLoginForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}


function loginAjax(){
var email=document.getElementById('email').value;
var pass=document.getElementById('password').value;
    jQuery.ajax({
          url: '../TestServlet',
          type: 'POST',
          data: '{uname:"'.concat(email).concat('",pass:"').concat(pass).concat('",email:"",action:"login"}'),
          dataType: 'json',
          beforeSend: function(x) {
            
          },
          success: function(jsonObj) {
        	  console.log('Value of result:'.concat(jsonObj));
        	  alert('Response received. Result'.concat(jsonObj));
        	  var response=jsonObj.result;
        	  var successFName=jsonObj.fname;
        	  if(response=='success')
        	  {
        		  alert('Success. Fullname is: '.concat(successFName));
        		  shakeModal101();
        		  $('#loginModal .modal-dialog').close();
        	  }
        		  
        	  else
        		  /*alert('Failure. Please try again.');*/
        		  shakeModal();
          }
    });

	    /*$.post("../TestServlet",{"uname":"abc","pass":"def"},function(data){*/

	        /* perform your task here. */

	   /*  },"json");
	
    /*   Remove this comments when moving to server
    $.post( "/login", function( data ) {
            if(data == 1){
                window.location.replace("/home");            
            } else {
                 shakeModal(); 
            }
        });
    */

/*   Simulate error message from the server   */
    /*shakeModal();*/
}

function checkuname(){
    var z=/[^a-zA-Z0-9\!\@\#\$\^\_]+/;
    var usr=document.getElementById('uname').value;
    if(z.test(usr)||usr==null||usr==' '||usr<4||usr>25){
      shakeModal4();
    }else{
      shakeModal101();
    }
}

function checkfullname(){
    var y=/[^[a-zA-Z]+ [a-zA-Z]+$]/;
    var firstname=document.getElementById('fname').value;
    if(y.test(firstname)||firstname==null||firstname==" "||firstname<4||firstname>50){
      shakeModal5();
    }else{
      shakeModal101();
    }
}

function checkmail(){
    // var x = document.forms["myform"]["email"].value;
	var Email=document.getElementById('Email').value;
    var atpos = Email.indexOf("@");
    var dotpos = Email.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        shakeModal2();
    }
    else{
        shakeModal101();
    }
}

function checkpass(){
    var j=/[^a-zA-Z0-9]+/;
    var Pass=document.getElementById('Password').value;
    if(j.test(Pass)||Pass==null||Pass==' '||Pass.length<8||Pass.length>16){
      shakeModal3();
    }else{
      shakeModal101();
    }
}

function matchPass()
{
    var pass1=document.getElementById('Password').value;
    var pass2=document.getElementById('password_confirmation').value;
    if(pass1!=pass2)
    {
        shakeModal1();
    }
    else{
        shakeModal101();
    }
}

function registerAjax(){
    var regPass=document.getElementById('Password').value;
    var firstname=document.getElementById('fname').value;
    console.log(firstname);
    var regEmail=document.getElementById('Email').value;
    var regUser=document.getElementById('uname').value;
    
    jQuery.ajax({
          url: '../TestServlet',
          type: 'POST',
          data: '{uname:"'.concat(regUser).concat('",fname:"').concat(firstname).concat('",pass:"').concat(regPass).concat('",email:"').concat(regEmail).concat('",action:"register"}'),
          dataType: 'json',
          beforeSend: function(x) {
            
          },
          success: function(result) {
         registerPass="";
         registerEmail="";
          }
    });
    
    
    alert('Registration Successful! click the OK to continue to Login page!');
    
    $('#loginModal .registerBox').fadeOut('fast',function(){
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast',function(){
            $('.login-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Login!');
    });       
     $('.error').removeClass('alert alert-danger').html('');
}


function shakeModal(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Entered Email Id or password is incorrect");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}

function shakeModal1(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("confirmation password must be same as the entered password");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}
function shakeModal2(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Please enter a valid email address");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}
function shakeModal3(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Password should not have anything other than a-z,A-Z,0-9 and must be 8-16 characters long");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}
function shakeModal4(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Username should be 4-25 characters long and should not have anything else other than a-z,A-Z,0-9,!,@,$,^,_");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}
function shakeModal5(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Fullname should only contain letters!");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 250 ); 
}

function shakeModal101(){
    $('.error').removeClass('alert').html('');
}

 