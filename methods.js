class Customer{
    constructor(accno,acctype,cname,bal){
        this.accno = accno;
        this.acctype = acctype;
        this.cname = cname;
        this.bal = bal;
    }
}
var customers = new Map();

function login(){
    event.preventDefault();
    if(usersList.has(uname.value) && password.value == usersList.get(uname.value)){
        swal.fire("Login","Login successful","success")
        .then(function(){
            localStorage.setItem("username",uname.value);
            localStorage.setItem("password",password.value);
            uname.value="";
            password.value="";
            
            window.location="index.html";
        })
    }else{
        swal.fire("Login","Login Failed","error");
    }
}

function createCustomer(){
    event.preventDefault();
    var cname = document.getElementById("cname").value;
    var acctype= document.getElementById("acctype").value;
    var accno = document.getElementById("accno").value;
    var bal = document.getElementById("bal").value;
    var regexpacc = /^\d{4}$/;
    
    if(regexpacc.test(accno) == false){
        document.getElementById("acc_err_create").innerHTML = "Account number must be 4 digits";
        document.getElementById("acc_err_create").style.visibility = "visible";
    }
    else if(customers.has(accno)!=false){
        document.getElementById("acc_err_create").innerHTML = "Account number already exists";
        document.getElementById("acc_err_create").style.visibility = "visible";
    }
    else if(acctype != "Savings" && acctype != "Fixed Deposit" && acctype != "Current" && acctype != "Recurring Deposit"){
        document.getElementById("acctype_err_create").style.visibility = "visible";
        document.getElementById("acc_err_create").style.visibility = "hidden";
    }
    else if(bal<1000){
        document.getElementById("bal_err_create").style.visibility = "visible";
        document.getElementById("acc_err_create").style.visibility = "hidden";
    }
    else{
        document.getElementById("acc_err_create").style.visibility = "hidden";
        document.getElementById("name_err_create").style.visibility = "hidden";
        document.getElementById("acctype_err_create").style.visibility = "hidden";
        document.getElementById("bal_err_create").style.visibility = "hidden";
        var customer = new Customer(accno,acctype,cname,bal);
        customers.set(accno,customer);
        console.log(customer);
        // setTimeout(alertMessage,1000,"customer created successfully...");
        swal.fire("Congratulations!!!","Your account has been created successfully","success").then(function(){
            document.getElementById("accno").value="";
            document.getElementById("cname").value="";
            document.getElementById("acctype").value="";
            document.getElementById("bal").value="";
        });
        document.getElementById("create_customer").style.display = "none";
    }
}

// function getDetails(){
//     event.preventDefault();
//     var accno = document.getElementById("accno_details").value;
//     var regexpacc = /^\d{4}$/;
//     if(customers.has(accno) == false){
//         document.getElementById("accno_err_get").innerHTML = "Invalid Account number";
//         document.getElementById("accno_err_get").style.visibility = "visible";
//     }
//     else{
//         document.getElementById("accno_err_get").style.visibility = "hidden";
//         document.getElementById("accno_details").value = "";
//         setTimeout(alertMessage,500,customers.get(accno)['cname'],customers.get(accno)["accno"],customers.get(accno)["acctype"],customers.get(accno)["bal"]);
//         document.getElementById("customer_details").style.display = "none";
//         console.log(customers.get(accno));
//     }
    
// }

function printDetails(){
    if(customers.size ==0 ){
        console.log("No customers found");
    }
    else{
        for(let c of customers){
            console.log(c);
        }
    }
}

function transaction_submit(){
    event.preventDefault();
    var ttype= document.getElementById("ttype").value;
    var taccno = document.getElementById("taccno").value;
    var tamt = parseInt(document.getElementById("tamt").value);

    if(customers.has(taccno) == false){
        document.getElementById("accno_err_transact").innerHTML = "Invalid Account number";
        document.getElementById("accno_err_transact").style.visibility = "visible";
    }
    else if(ttype!="Withdraw" && ttype!="Deposit"){
        document.getElementById("ttype_err").innerHTML = "Invalid transaction type";
        document.getElementById("ttype_err").style.visibility = "visible";
    }
    else if(ttype=="Withdraw"){
        var bal = parseInt(customers.get(taccno)["bal"]);
        if(tamt%100 != 0){
            document.getElementById("tamt_err").innerHTML = "Transaction Amount must be multiples of 100 only";
            document.getElementById("tamt_err").style.visibility = "visible";
        }
        else if(tamt >= bal){
            document.getElementById("tamt_err").innerHTML = "Your balance is less than what you have entered";
            document.getElementById("tamt_err").style.visibility = "visible";
        }
        else{
            var customer = customers.get(taccno);
            var cname = customer["cname"];
            var accno = customer["accno"];
            var acctype = customer["acctype"];
            customers.set(accno,{cname:cname,accno:accno,acctype:acctype,bal:bal-tamt});
            swal.fire("Withdraw Success ",customers.get(taccno)["bal"],"success").then(function(){

                document.getElementById("ttype").value = "";
                document.getElementById("taccno").value = "";
                document.getElementById("tamt").value = "";
                document.getElementById("accno_err_transact").style.visibility = "hidden";
                document.getElementById("ttype_err").style.visibility = "hidden";
                document.getElementById("tamt_err").style.visibility = "hidden";
                document.getElementById("transaction").style.display = "none";
            });
            
        }
    }
    else if(ttype=="Deposit"){
        var bal = parseInt(customers.get(taccno)["bal"]);
        if(tamt>50000){
            document.getElementById("tamt_err").innerHTML = "Deposit amount must be less than or equal to 50000";
            document.getElementById("tamt_err").style.visibility = "visible";
        }
        else{
            var customer = customers.get(taccno);
            var cname = customer["cname"];
            var accno = customer["accno"];
            var acctype = customer["acctype"];
            customers.set(accno,{cname:cname,accno:accno,acctype:acctype,bal:bal+tamt});
            swal.fire("Deposit Success ",customers.get(taccno)["bal"],"success").then(function(){
                document.getElementById("ttype").value = "";
                document.getElementById("taccno").value = "";
                document.getElementById("tamt").value = "";
                document.getElementById("accno_err_transact").style.visibility = "hidden";
                document.getElementById("ttype_err").style.visibility = "hidden";
                document.getElementById("tamt_err").style.visibility = "hidden";
            });
        }
    }
    else{
        document.getElementById("accno_err_transact").style.visibility = "visible";
        document.getElementById("ttype_err").style.visibility = "visible";
        document.getElementById("tamt_err").style.visibility = "visible";
    }
}

// function deleteDetails(){
//     event.preventDefault();
//     var accno = document.getElementById("delete_accno").value;
//     if(customers.has(accno) == false){
//         document.getElementById("accno_delete_err").style.visibility = "visible";
//     }
//     else{
//         customers.delete(accno);
//         document.getElementById("delete_accno").value = "";
//         // setTimeout(alertMessage,1000,"Account deleted successfully...");
//         swal.fire("Congratulations!!!","Your account has been created successfully","success")
//         .then(function(){
//             document.getElementById("delete_customer").style.display = "none";
//         });
//     }
// }

function sign_out(){
    swal.fire({
        title: 'Are you sure?',
        type: 'warning',
        showCancelButton: true,
        cancelButtonColor: 'red',
        confirmButtonColor: 'green',
        cancelButtonText:'No',
        confirmButtonText: 'Yes'
      }).then((result) =>{
        if(result.isConfirmed){
            if(localStorage.getItem("username") != undefined){
                var uname = localStorage.getItem("username");
                localStorage.removeItem("username");
            }
            if(localStorage.getItem("password")!=undefined){
                var password = localStorage.getItem("password");
                localStorage.removeItem("password");
            }
            document.getElementById("login").style.display = "block";
            document.getElementById("right").style.display = "none";
            swal.fire("","Signout successfully","success").
                then(function(){
                    window.location = "login.html";
                });
        }
    })
}

function alertMessage(...x){
    var msg ="";
    for(const a of x){
        msg = msg+" "+a;
    }
    setTimeout(function(){
        alert(msg)
    },1000);
}

function getWindowSize(){
    var x = document.getElementById("screen");
    var w = window.innerWidth;
    var h = window.innerHeight;
    x.innerHTML = + w + "x" + h;
}