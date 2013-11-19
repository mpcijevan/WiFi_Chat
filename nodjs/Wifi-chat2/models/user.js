exports.user = function(){
    var name;
    var surname;
    var date;
    var mail;
    var gender;
    var password;
    var nikname;
    
    this.get_name = function(){
        return this.name;
    }
    this.set_name = function(name){
        this.name = name;
    }
    this.get_surname = function(){
        return this.surname;
    }
    this.set_surname = function(surname){
        this.surname = surname;
    }
    this.get_date = function(){
        return this.date;
    }
    this.set_date = function(date){
        this.date = date;
    }
    this.get_mail = function(){
        return this.mail;
    }
    this.set_mail = function(mail){
        this.mail = mail;
    }
    this.get_gender = function(){
        return this.gender;
    }
    this.set_gender = function(gender){
        this.gender = gender;
    }
    this.get_password = function(){
        return this.password;
    }
    this.set_password = function(password){
        this.password = password;
    }
    this.get_nikname = function(){
        return this.nikname;
    }
    this.set_nikname = function(nikname){
        this.nikname = nikname;
    }
}