//
//  IosLocalStorage.swift
//  invoicer
//
//  Created by Lucca Beurmann on 21/05/25.
//

import invoicerShared

final class IosLocalStorage: LocalStorage {
    
    private let secureStorage: SecureStorageProtocol
    
    init(secureStorage: SecureStorageProtocol) {
        self.secureStorage = secureStorage
    }
    
    func clear(key: String) {
        
    }
    
    func getString(key: String) -> String? {
        return secureStorage.getString(forKey: key)
    }
    
    func setString(value: String, key: String) {
        return secureStorage.setString(value: value, forKey: key)
    }
}
