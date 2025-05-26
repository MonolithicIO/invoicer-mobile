//
//  SecureStorage.swift
//  invoicer
//
//  Created by Lucca Beurmann on 26/05/25.
//

import Foundation

protocol SecureStorageProtocol {
    func setString(value: String, forKey: String)
    func getString(forKey: String) -> String?
    func remove(key: String)
}

// Migrate to Keychain
final class SecureStorage: SecureStorageProtocol {
    
    func remove(key: String) {
        UserDefaults.standard.removeObject(forKey: key)
    }
    
    func setString(value: String, forKey: String) {
        UserDefaults.standard.set(value, forKey: forKey)
    }
    
    func getString(forKey: String) -> String? {
        return UserDefaults.standard.string(forKey: forKey)
    }
}
