var account;
window.addEventListener('load', async () => {


    if (typeof window.ethereum !== 'undefined') {
        console.log("MetaMask is Available :) !");
    }

    // Modern DApp browsers
    if (window.ethereum) {
        window.web3 = new Web3(ethereum);

        // To prevent the page reloading when the MetaMask network changes
        ethereum.autoRefreshOnNetworkChange = false;

        // To Capture the account details from MetaMask
        const accounts = await ethereum.enable();
        account = accounts[0];

    }
    // Legacy DApp browsers
    else if (window.web3) {
        //window.web3 = new Web3(web3.currentProvider);
        window.web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/cbd9dc11b30147e9a2cc974be655ef7c"));
    }
    // Non-DApp browsers
    else {
        console.log('Non-Ethereum browser detected. Please install MetaMask');
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
        }
    ]
var contractaddress = '0x07eDa0230fe05480093176041e228D8A05fC9ac8';

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
        if (err) { console.log(err); }
        if (result) { document.getElementById("result").innerHTML = result; }
    });
}

function show_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var idd = document.getElementById("tid").value;
    var result = myContract.methods.retreive_donor_details(idd).call(function (err, result) {
        if (err) { console.log(err); }
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
        if (err) { console.log(err); }
        if (results) {
            document.getElementById("get_count").innerHTML = results;
            console.log(results);
        }
    });
}


function return_bg_count() {
    var myContract = new web3.eth.Contract(abi, contractaddress, { from: account, gasPrice: '5000000', gas: '500000' });
    var result = myContract.methods.return_bg_data().call(function (err, result) {
        if (err) { console.log(err); }
        if (result) {
            console.log(result);
            document.getElementById("O+").innerHTML = result[0];
            document.getElementById("O-").innerHTML = result[1];
            document.getElementById("A+").innerHTML = result[2];
            document.getElementById("A-").innerHTML = result[3];
            document.getElementById("B+").innerHTML = result[4];
            document.getElementById("B-").innerHTML = result[5];
            document.getElementById("AB+").innerHTML = result[6];
            document.getElementById("AB-").innerHTML = result[7];
        }
    });
}

function PageLoad() {
    return_bg_count();
    returns_donor_number();
}
window.onload = PageLoad;