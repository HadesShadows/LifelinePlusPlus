var account;

window.addEventListener('load', async () => {
    // Modern DApp browsers
    if (window.ethereum) {
        window.web3 = new Web3(ethereum);

        // To prevent the page reloading when the MetaMask network changes
        ethereum.autoRefreshOnNetworkChange = true;

        // To Capture the account details from MetaMask

        // const accounts = await ethereum.enable();
        const accounts = await ethereum.enable();
        account = accounts[0];
        document.getElementById("content").innerHTML = account;
        get_request_details();
        return_bg_count();
        returns_request_number();
        returns_donor_number();
        giver_details();
    }
});


var abi =
    [
        {
            "inputs": [
                {
                    "internalType": "string",
                    "name": "_donor_name",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "_donor_address",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "_donor_age",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "_donor_bg_grp",
                    "type": "string"
                },
            ],
            "name": "store_donor_details",
            "outputs": [],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "return_bg_data",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                },
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "return_donor_count",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "stateMutability": "nonpayable",
            "type": "constructor"
        },
        {
            "inputs": [
                {
                    "internalType": "uint256",
                    "name": "donor_id",
                    "type": "uint256"
                }
            ],
            "name": "retreive_donor_details",
            "outputs": [
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "return_request_count",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "request_blood",
            "outputs": [],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "return_request_details",
            "outputs": [
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "donate_blood",
            "outputs": [],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "return_giver_details",
            "outputs": [
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "",
                    "type": "string"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        }
    ]

var contractaddress = '0x0adB290c999d4898BC26F988CC1648D3Cd0e8e24';


function add_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var e = document.getElementById("blood_list");
    var value = e.options[e.selectedIndex].value;
    var text = e.options[e.selectedIndex].text;
    var ds1 = document.getElementById("city").value;
    var ds2 = document.getElementById("age").value;
    var tname = document.getElementById("name1").value;
    var result = myContract.methods.store_donor_details(tname, ds1, ds2, text).send(function (err, result) {
        console.log(myContract);
        // if (err) { console.log(err); }
        if (result) { document.getElementById("result").innerHTML = result; }
    });
}

function show_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    // console.log(account);
    var idd = document.getElementById("tid").value;
    var result = myContract.methods.retreive_donor_details(idd).call(function (err, result) {
        // if (err) { console.log(err); }
        if (result) {
            console.log(result);
            document.getElementById("get_name").innerHTML = result[0];
            document.getElementById("get_city").innerHTML = result[1];
            document.getElementById("get_age").innerHTML = result[2];
            document.getElementById("get_bg_grp").innerHTML = result[3];
        }
    });
}

function returns_donor_number() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var results = myContract.methods.return_donor_count().call(function (err, results) {
        // if (err) { console.log(err); }
        if (results) {
            document.getElementById("get_count").innerHTML = results;
            // console.log(results);
        }
    });
}


function return_bg_count() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.return_bg_data().call(function (err, result) {
        if (err) { console.log(err); }
        if (result) {
            console.log(result);
            document.getElementById("A").innerHTML = result[0];
            document.getElementById("B").innerHTML = result[1];
            document.getElementById("C").innerHTML = result[2];
            document.getElementById("D").innerHTML = result[3];
            document.getElementById("E").innerHTML = result[4];
            document.getElementById("F").innerHTML = result[5];
            document.getElementById("G").innerHTML = result[6];
            document.getElementById("H").innerHTML = result[7];
        }
    });
}

function returns_request_number() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var results = myContract.methods.return_request_count().call(function (err, results) {
        // if (err) { console.log(err); }
        if (results) {
            document.getElementById("get_request_count").innerHTML = results;
        }
    });
}

function requests_blood() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.request_blood().send(function (err, result) {
        // if (err) { console.log(err); }
        if (result) { document.getElementById("result").innerHTML = result; }
    });
}

function get_request_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.return_request_details().call(function (err, result) {
        // if (err) { console.log(err); }
        if (result) {
            document.getElementById("get_names").innerHTML = result[0];
            document.getElementById("get_citys").innerHTML = result[1];
            document.getElementById("get_ages").innerHTML = result[2];
            document.getElementById("get_bg_grps").innerHTML = result[3];
        }
    });
}

function blood_donate() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.donate_blood().send(function (err,result) { 
        if (err) 
        { 
            console.log(err);
        }
        if (result) 
        { 
            console.log(result); 
        }
    });
}

function giver_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.return_giver_details().call(function (err, result) {
        // if (err) { console.log(err); }
        if (result) {
            console.log(result);
            document.getElementById("get_name_giver").innerHTML = result[0];
            document.getElementById("get_city_giver").innerHTML = result[1];
            document.getElementById("get_age_giver").innerHTML = result[2];
        }
    });
}
