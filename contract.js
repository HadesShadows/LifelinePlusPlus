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
        console.log(account);

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
            }
        ],
        "name": "store_donor_details",
        "outputs": [],
        "stateMutability": "nonpayable",
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
            }
        ],
        "stateMutability": "view",
        "type": "function"
    }
]
var contractaddress = '0x9F960aA7A608037fCb78b5d0Dd4FD4bc7858613d';

function add_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, {from: account, gasPrice: '5000000', gas: '500000'});
    var ds1 = document.getElementById("addr").value;
    var ds2 = document.getElementById("age").value;
    var tname = document.getElementById("name1").value;
    var result = myContract.methods.store_donor_details(tname, ds1, ds2).send(function (err, result) {
        console.log(myContract);
        if (err) { console.log(err); }
        if (result) { document.getElementById("result").innerHTML = result; }
    });
}

function show_details() {
    var myContract = new web3.eth.Contract(abi, contractaddress, {from: account, gasPrice: '5000000', gas: '500000'});
    var idd = document.getElementById("tid").value;
    var result = myContract.methods.retreive_donor_details(idd).call(function (err, result) {
        if (err) { console.log(err); }
        if (result) {
            document.getElementById("get_name").innerHTML = result[0];
            document.getElementById("get_addr").innerHTML = result[1];
            document.getElementById("get_age").innerHTML = result[2];
        }
    });
}