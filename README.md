# Cyber-Security
RSA is the most Secure Encryption Algorithm but Using RSA algorithm for data encryption is a time consuming process. The RSA algorithm is 10x slower than the DES algorithm. While, the drawback of DES or any Private key algorithm is sharing of secret / key. We proposed a hybrid algorithm that uses RSA encryption to encrypt the private key of the user and applies normal round encryption to the data with round specific keys generated from the user private key. The RSA algorithm is not used for data encryption in this proposed hybrid algorithm which makes the process faster than the traditional RSA algorithm. In decryption, the user needs to provide the RSA-Encrypted key which is decrypted and data is decrypted like the encryption process. The implementation of the proposed algorithm system will be done in Java language.
