//
//  IosAnalyticsTracker.swift
//  invoicer
//
//  Created by Lucca Beurmann on 21/05/25.
//

import invoicerShared
import FirebaseAnalytics

final class IosAnalyticsTracker: AnalyticsTracker {
    func track(event: invoicerShared.AnalyticsEvent) {
        Analytics.logEvent(
            event.name,
            parameters: event.params
        )
    }
}
